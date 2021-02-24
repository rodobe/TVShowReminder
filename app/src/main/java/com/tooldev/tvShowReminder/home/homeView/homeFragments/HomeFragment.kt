package com.tooldev.tvShowReminder.home.homeView.homeFragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import com.tooldev.tvShowReminder.R
import com.tooldev.tvShowReminder.content.contentView.activities.TvShowsDetailActivity
import com.tooldev.tvShowReminder.data.model.response.home.GenresResult
import com.tooldev.tvShowReminder.databinding.FragmentHomeBinding
import com.tooldev.tvShowReminder.data.model.response.home.TvShow
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails
import com.tooldev.tvShowReminder.home.homeView.adapters.OnAirTvShowAdapter
import com.tooldev.tvShowReminder.home.homeView.adapters.SubscribedTvShowsAdapter
import com.tooldev.tvShowReminder.home.homeView.homeViewModels.HomeViewModel
import com.tooldev.tvShowReminder.util.HorizontalSectionAdapter
import com.tooldev.tvShowReminder.util.VerticalSectionAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable


@AndroidEntryPoint
class HomeFragment: Fragment(){

    private lateinit var viewModel: HomeViewModel
    private val compositeSubscription = CompositeDisposable()
    private lateinit var binding: FragmentHomeBinding
    private val adapter = ConcatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding. lifecycleOwner= viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        setListenerSubjects()
        setAdapter()
        viewModel.start()
    }

    private fun setListenerSubjects(){
        compositeSubscription.add(viewModel.tvShowPublishSubject.subscribe {
            showTvResults(it)
        })
        compositeSubscription.add(viewModel.subscribedTvShowPublishSubject.subscribe {
            showSubscribedTvShows(it)
        })

        compositeSubscription.add(viewModel.genresPublishSubject.subscribe {
            showGenres(it)
        })
    }

    private fun setAdapter(){
        binding.recyclerHomeTvshows.adapter = adapter
    }

    private fun showTvResults(tvShowsResult: List<TvShow>){
        adapter.addAdapter(VerticalSectionAdapter("TODAS", OnAirTvShowAdapter(tvShowsResult) { it ->
            tvShows(it)
        }))
        binding.recyclerHomeTvshows.adapter?.notifyDataSetChanged()
        viewModel.getGenres()
    }

    private fun showGenres(genresResult: GenresResult){
            val onAirAdapter = adapter.adapters.find { adapter -> adapter is VerticalSectionAdapter }
            (onAirAdapter as VerticalSectionAdapter).showGenres(genresResult)
    }

    private fun showSubscribedTvShows(tvShowsResult: List<TvShowDetails>){
        if (adapter.itemCount > 0) {
            adapter.addAdapter(1, HorizontalSectionAdapter("SUSCRIPTAS", SubscribedTvShowsAdapter(tvShowsResult)))
        } else {
            adapter.addAdapter(HorizontalSectionAdapter("SUSCRIPTAS", SubscribedTvShowsAdapter(tvShowsResult)))
        }
        binding.recyclerHomeTvshows.adapter?.notifyDataSetChanged()
    }

    private fun tvShows(tvId: Int){
        val intent = Intent(context, TvShowsDetailActivity::class.java)
        intent.putExtra("tvId", tvId)
        startActivity(intent)
    }

    companion object{
        fun getInstance(): HomeFragment{
            return HomeFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_home_menu, menu)
        val menuItem =menu.findItem(R.id.search_icon)
        val searchView = menuItem.actionView as SearchView
    }

    override fun onResume() {
        super.onResume()
    }

}