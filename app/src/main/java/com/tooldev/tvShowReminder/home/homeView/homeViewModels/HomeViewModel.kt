package com.tooldev.tvShowReminder.home.homeView.homeViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tooldev.tvShowReminder.data.api.Result
import com.tooldev.tvShowReminder.data.model.response.home.GenresResult
import com.tooldev.tvShowReminder.data.model.response.home.TvShow
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails
import com.tooldev.tvShowReminder.data.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ContentRepository): ViewModel() {

    val tvShowPublishSubject: PublishSubject<List<TvShow>> = PublishSubject.create()
    val subscribedTvShowPublishSubject: PublishSubject<List<TvShowDetails>> = PublishSubject.create()
    val genresPublishSubject: PublishSubject<GenresResult> = PublishSubject.create()
    private lateinit var tvShows: List<TvShow>

    fun start() {
        getTvShows(1)
        getSubscribedTvShows()
    }

    fun getGenres(){
        viewModelScope.launch {
            repository.getGenres().let { result ->
                if (result is Result.Success){
                    if (result.data != null) {
                        showGenres(result.data)
                    } else{
                        showResultNotFound()
                    }
                } else{
                    onError()
                }
            }
        }
    }

    fun getTvShows(page: Int?){
        viewModelScope.launch {
            repository.getTvShows(page).let { result ->
                if (result is Result.Success){
                    if (result.data != null) {
                        tvShows = result.data
                        showTvShows(result.data)
                    } else{
                        showResultNotFound()
                    }
                } else{
                    onError()
                }
            }
        }
    }

    fun getSubscribedTvShows(){
        viewModelScope.launch {
            repository.getSubscirbedTvShows().let { result ->
                if (result is Result.Success){
                    if (result.data != null) {
                        showSubscribedTvShows(result.data)
                    } else{
                        showResultNotFound()
                    }
                } else{
                    onError()
                }
            }
        }
    }

    private fun showGenres(genresResult: GenresResult){
        genresPublishSubject.onNext(genresResult)
    }

    private fun showTvShows(tvShowsResult: List<TvShow>){
        tvShowPublishSubject.onNext(tvShowsResult)
    }

    private fun showSubscribedTvShows(tvShowsResult: List<TvShowDetails>){
        subscribedTvShowPublishSubject.onNext(tvShowsResult)
    }

    private fun showResultNotFound(){

    }

    private fun onError(){

    }

}