package com.tooldev.wabiPlay.splash.splashView.splashViewModels

import android.os.Handler
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.tooldev.wabiPlay.data.repository.ContentRepository
import io.reactivex.subjects.PublishSubject

class SplashViewModel @ViewModelInject constructor(private val contentRepository: ContentRepository): ViewModel() {


    val splashPublishSubject: PublishSubject<Boolean> = PublishSubject.create()

    init {
        getMovies()
    }

    private fun getMovies(){
        Handler().postDelayed({
            goToHome()
        }, 3000)
    }

    private fun goToHome(){
        splashPublishSubject.onNext(true)
    }

}