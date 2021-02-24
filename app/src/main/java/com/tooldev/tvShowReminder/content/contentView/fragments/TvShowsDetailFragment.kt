package com.tooldev.tvShowReminder.content.contentView.fragments

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import androidx.transition.TransitionManager
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.tooldev.tvShowReminder.R
import com.tooldev.tvShowReminder.content.contentView.activities.TvShowsDetailActivity
import com.tooldev.tvShowReminder.content.contentView.viewModels.TvShowsDetailViewModel
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails
import com.tooldev.tvShowReminder.databinding.FragmentTvshowDetailBinding
import com.tooldev.tvShowReminder.util.CustomScrollView
import com.tooldev.tvShowReminder.util.changeImageProperties
import com.tooldev.tvShowReminder.util.getContrastColor
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class TvShowsDetailFragment: Fragment(), CustomScrollView.OnScrollChangeListener {

    private lateinit var viewModel: TvShowsDetailViewModel
    private lateinit var binding: FragmentTvshowDetailBinding
    private val compositeDisposable = CompositeDisposable()
    var posterPath: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(TvShowsDetailViewModel::class.java)
        binding = FragmentTvshowDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding. lifecycleOwner= viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        setAppBar()
        setListenersSunjects()
        setOnScrollListener()
        binding.viewModel?.start(tvId)
    }

    private fun setAppBar(){
        (activity as TvShowsDetailActivity).setSupportActionBar(binding.toolbarTvshow)
        (activity as TvShowsDetailActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as TvShowsDetailActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as TvShowsDetailActivity).supportActionBar?.title = null
    }

    private fun setListenersSunjects(){
        compositeDisposable.add(viewModel.resultPublishSubject.subscribe() {
            showTvShowDetails(it)
        })
    }

    private fun setOnScrollListener(){
        binding.nestedscrolleTvshow.setOnScrollChangeListener(this)
    }

    private fun showTvShowDetails(tvShowsDetails: TvShowDetails){
        posterPath = tvShowsDetails.poster_path
        setPoster(tvShowsDetails.poster_path)
        setTitle(tvShowsDetails.name)
        setYear(tvShowsDetails.first_air_date)
        setOverview(tvShowsDetails.overview)
    }

    private fun setTitle(title: String){
        binding.textviewTitle.text = title
    }

    private fun setYear(year: String){
        binding.textviewYear.text = year
    }

    private fun setOverview(overview: String){
        binding.textviewOverviewDetail.text = overview
    }

    private fun setPoster(poster: String){
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + poster).into(object : Target {

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                getDominantColor(bitmap)
                binding.imageviewPoster.setImageBitmap(bitmap)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

            }

        })
    }

    private fun getDominantColor(bitmap: Bitmap?){
        if (bitmap != null) {
            Palette.Builder(bitmap).generate {
                setColors(it)
            }
        }
    }

    private fun setColors(palette: Palette?){
        binding.linearTvshowOverview.setBackgroundColor(palette?.dominantSwatch?.rgb ?: ContextCompat.getColor(
            requireContext(),
            R.color.black_two
        ))
        binding.textviewTitle.setTextColor(context?.getContrastColor(palette) ?: ContextCompat.getColor(
            requireContext(),
            R.color.black_two
        ))
        binding.textviewYear.setTextColor(context?.getContrastColor(palette) ?: ContextCompat.getColor(
            requireContext(),
            R.color.black_two
        ))
        binding.materialbuttonSuscripto.setTextColor(palette?.dominantSwatch?.rgb ?: ContextCompat.getColor(
            requireContext(),
            R.color.black_two
        ))
        binding.materialbuttonSuscripto.strokeColor = ColorStateList.valueOf(context?.getContrastColor(palette) ?: ContextCompat.getColor(
            requireContext(),
            R.color.black_two
        ))
        binding.materialbuttonSuscripto.backgroundTintList = ColorStateList.valueOf(context?.getContrastColor(palette) ?: ContextCompat.getColor(
            requireContext(),
            R.color.black_two
        ))
        binding.textviewOverview.setTextColor(context?.getContrastColor(palette) ?: ContextCompat.getColor(
            requireContext(),
            R.color.black_two
        ))
        binding.textviewOverviewDetail.setTextColor(context?.getContrastColor(palette) ?: ContextCompat.getColor(
            requireContext(),
            R.color.black_two
        ))
    }

    companion object{
        var tvId: Int = 0

        fun getInstance(tvId: Int): TvShowsDetailFragment {
            this.tvId = tvId
            return TvShowsDetailFragment()
        }
    }

    override fun onScrollChange(
        v: CustomScrollView?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int,
        event: MotionEvent?
    ) {
        val transition = changeImageProperties()

        if (oldScrollY < scrollY && event?.action == MotionEvent.ACTION_MOVE) {
            TransitionManager.beginDelayedTransition(
                binding.cardViewPoster,
                transition.setDuration(1000L)
            )
            Picasso.get().load("https://image.tmdb.org/t/p/w200/" + posterPath)
                .into(binding.imageviewPoster)
        } else {
            if (oldScrollY > scrollY && event?.action != MotionEvent.ACTION_UP) {
                TransitionManager.beginDelayedTransition(
                    binding.cardViewPoster,
                    transition.setDuration(1000L)
                )
                Picasso.get().load("https://image.tmdb.org/t/p/w500/" + posterPath)
                    .into(binding.imageviewPoster)
            }
        }
    }

}