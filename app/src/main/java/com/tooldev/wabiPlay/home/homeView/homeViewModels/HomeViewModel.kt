package com.tooldev.wabiPlay.home.homeView.homeViewModels

import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.reactivex.subjects.PublishSubject


class HomeViewModel @ViewModelInject constructor(): ViewModel() {


    val searchObservableField: ObservableField<String> = ObservableField()
    val moviesObservableField: ObservableField<String> = ObservableField()
    val seriesObservableField: ObservableField<String> = ObservableField()
    val episodesObservableField: ObservableField<String> = ObservableField()
    val searchPublishSubject: PublishSubject<Bundle> = PublishSubject.create()
    var moviesChecked = false
    var seriesChecked = false
    var episodesChecked = false

    init {
        initViews()
    }

    private fun initViews(){
        moviesObservableField.set("Movie")
        seriesObservableField.set("Series")
        episodesObservableField.set("Episode")
    }

    fun search(){
        searchPublishSubject.onNext(createSearch())
    }

    private fun createSearch(): Bundle{
        val bundle = Bundle()
        bundle.putString("search", searchObservableField.get()!!)
        bundle.putString("type", getContentType())
        return bundle
    }

    private fun getContentType(): String?{
        return if (moviesChecked){
            moviesObservableField.get()
        } else{
            if (seriesChecked){
                seriesObservableField.get()
            } else {
                if (episodesChecked){
                    episodesObservableField.get()
                } else{
                    null
                }
            }
        }
    }

}