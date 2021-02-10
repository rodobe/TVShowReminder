package com.tooldev.wabiPlay.home.homeView.homeFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.tooldev.wabiPlay.R
import com.tooldev.wabiPlay.data.model.response.Title
import com.tooldev.wabiPlay.databinding.FragmentHomeBinding
import com.tooldev.wabiPlay.content.contentView.contentAdapters.ContentRecyclerAdapter
import com.tooldev.wabiPlay.content.contentView.contentFragments.ContentFragment
import com.tooldev.wabiPlay.home.homeView.homeActivities.HomeActivity
import com.tooldev.wabiPlay.util.inTransaction
import com.tooldev.wabiPlay.home.homeView.homeViewModels.HomeViewModel
import com.tooldev.wabiPlay.util.inTransactionAdd
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable


@AndroidEntryPoint
class HomeFragment: Fragment(){

    private val viewModel: HomeViewModel by viewModels()
    private val compositeSubscription = CompositeDisposable()
    private lateinit var binding: FragmentHomeBinding
    private val adapter = ConcatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
    }

    private fun setListenerSubjects(){
        compositeSubscription.add(viewModel.searchPublishSubject.subscribe {
            showContent(it)
        })
        compositeSubscription.add(RxCompoundButton.checkedChanges(binding.checkboxMovies).subscribe(){
            updateCheckBoxState("movies")
        })

        compositeSubscription.add(RxCompoundButton.checkedChanges(binding.checkboxSeries).subscribe(){
            updateCheckBoxState("series")
        })

        compositeSubscription.add(RxCompoundButton.checkedChanges(binding.checkboxEpisodes).subscribe(){
            updateCheckBoxState("episodes")
        })
    }

    private fun showContent(bundle: Bundle){
        (activity as HomeActivity).supportFragmentManager.inTransactionAdd{ add(R.id.contentFrame, ContentFragment.getInstance(bundle)) }
    }

    private fun updateCheckBoxState(type: String){
        when (type){
            "movies" -> changeCheckBoxStateSeriesEpisodes()
            "series" -> changeCheckBoxStateMoviesEpisodes()
            "episodes" -> changeCheckBoxStateMoviesSeries()
        }
    }

    private fun changeCheckBoxStateSeriesEpisodes(){
        if (binding.checkboxMovies.isChecked){
            binding.viewModel?.moviesChecked = true
            binding.checkboxSeries.isEnabled = false
            binding.checkboxEpisodes.isEnabled = false
        } else{
            binding.viewModel?.moviesChecked = false
            binding.checkboxSeries.isEnabled = true
            binding.checkboxEpisodes.isEnabled = true
        }
    }

    private fun changeCheckBoxStateMoviesEpisodes(){
        if (binding.checkboxSeries.isChecked){
            binding.viewModel?.seriesChecked = true
            binding.checkboxMovies.isEnabled = false
            binding.checkboxEpisodes.isEnabled = false
        } else{
            binding.viewModel?.seriesChecked = false
            binding.checkboxMovies.isEnabled = true
            binding.checkboxEpisodes.isEnabled = true
        }
    }
    private fun changeCheckBoxStateMoviesSeries(){
        if (binding.checkboxEpisodes.isChecked){
            binding.viewModel?.episodesChecked = true
            binding.checkboxSeries.isEnabled = false
            binding.checkboxMovies.isEnabled = false
        } else{
            binding.viewModel?.episodesChecked = false
            binding.checkboxSeries.isEnabled = true
            binding.checkboxMovies.isEnabled = true
        }
    }

    private fun onError(throwable: Throwable){

    }

    companion object{
        fun getInstance(): HomeFragment{
            return HomeFragment()
        }
    }

}