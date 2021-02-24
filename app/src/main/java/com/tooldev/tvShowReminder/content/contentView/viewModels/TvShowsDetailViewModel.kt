package com.tooldev.tvShowReminder.content.contentView.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tooldev.tvShowReminder.data.api.Result
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails
import com.tooldev.tvShowReminder.data.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsDetailViewModel @Inject constructor(private val repository: ContentRepository): ViewModel(){

    val resultPublishSubject: PublishSubject<TvShowDetails> = PublishSubject.create()
    private var tvShowDetails: TvShowDetails? = null

    fun start(tvId: Int){
        getTvShowDetails(tvId)
    }

    private fun getTvShowDetails(tvId: Int){
        viewModelScope.launch {
            repository.getTvShowDetails(tvId).let { result ->
                if (result is Result.Success){
                    if (result.data != null) {
                        tvShowDetails = result.data
                        showTvShow(result.data)
                    } else{
                        showResultNotFound()
                    }
                } else{
                    onError()
                }
            }
        }
    }

    fun subscribeTvShow(){
        viewModelScope.launch {
            repository.subscirbedTvShows(tvShowDetails)
        }
    }

    private fun showTvShow(tvShowDetails: TvShowDetails){
        resultPublishSubject.onNext(tvShowDetails)
    }

    private fun showResultNotFound(){

    }

    private fun onError(){

    }

}