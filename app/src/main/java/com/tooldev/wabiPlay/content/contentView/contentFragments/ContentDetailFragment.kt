package com.tooldev.wabiPlay.content.contentView.contentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import com.tooldev.wabiPlay.R
import com.tooldev.wabiPlay.home.homeView.homeActivities.HomeActivity
import com.tooldev.wabiPlay.content.contentView.contentViewModels.ContentDetailViewModel
import com.tooldev.wabiPlay.data.model.response.ContentResult
import com.tooldev.wabiPlay.data.model.response.Title
import com.tooldev.wabiPlay.databinding.FragmentContentDetailBinding
import com.tooldev.wabiPlay.util.CountriesLocations
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class ContentDetailFragment: Fragment(), OnMapReadyCallback {

    private val viewModel: ContentDetailViewModel by viewModels()
    private lateinit var binding: FragmentContentDetailBinding
    private val compositeDisposable = CompositeDisposable()
    private lateinit var title: Title
    private var releaseCountry: String = ""

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding. lifecycleOwner= viewLifecycleOwner
        binding.mapview.onCreate(savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        setAppBar()
        setListenersSunjects()
        binding.viewModel?.start()
    }

    private fun setAppBar(){
        (activity as HomeActivity).setSupportActionBar(binding.toolbarArticlesWeb)
        (activity as HomeActivity).actionBar?.setDisplayShowTitleEnabled(false)
        (activity as HomeActivity).actionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as HomeActivity).actionBar?.title = null
    }

    private fun setListenersSunjects(){
        compositeDisposable.add(viewModel.resultPublishSubject.subscribe(){
            setInfo(it)
        })
    }

    private fun setMap(){
        binding.mapview.getMapAsync(this)
    }
    private fun setInfo(title: Title){
        this.title = title
        getReleaseCountry(title.country)
        setMap()
        Picasso.get().load(title.poster).into(binding.imageviewPoster)
        binding.materialtextviewTitle.text = title.title
        binding.materialtextviewYear.text = title.year
        binding.materialtextviewRated.text = title.rated
        binding.materialtextviewReleased.text = title.released
        binding.materialtextviewRuntime.text = title.runtime
        binding.materialtexviewGenre.text = title.genre
        binding.materialtextviewDirector.text = title.director
        binding.materialtextviewWriter.text = title.writer
        binding.materialtextviewActors.text = title.actors
        binding.materialtextviewPlot.text = title.plot
        binding.materialtextviewLanguage.text = title.language
        binding.materialtextviewCountry.text = title.country
        binding.materialtextviewAwards.text = title.awards
        binding.materialtextviewMetascore.text = title.metascore
    }

    private fun getReleaseCountry(country: String){
        val countries = country.split(",")
        releaseCountry = countries[0]
    }

    companion object{
        var title: String? = null
        var type: String? = null
        var plot: String? = null

        fun getInstance(content: ContentResult): ContentDetailFragment {
            title = content.title
            type = content.type
            plot = "full"
            return ContentDetailFragment()
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.uiSettings?.setAllGesturesEnabled(false)
        p0?.addMarker(
            MarkerOptions()
                .position(CountriesLocations.countries[releaseCountry] ?: LatLng(36.191672, -41.655987))
                .title("Marker in Sydney")
        )
        p0?.moveCamera(CameraUpdateFactory.newLatLng(CountriesLocations.countries[releaseCountry] ?: LatLng(36.191672, -41.655987)))
    }

    override fun onResume() {
        super.onResume()
        binding.mapview.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapview.onPause()
    }
}