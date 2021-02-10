package com.tooldev.wabiPlay.splash.splashView.splashFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tooldev.wabiPlay.databinding.FragmentSplashBinding
import com.tooldev.wabiPlay.home.homeView.homeActivities.HomeActivity
import com.tooldev.wabiPlay.splash.splashView.splashViewModels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class SplashFragment: Fragment(){


    private val compositeSubscription = CompositeDisposable()
    private val viewModel: SplashViewModel by viewModels()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        setListeners()
    }

    private fun setListeners(){
        compositeSubscription.add(viewModel.splashPublishSubject.subscribe {
            goToHome(it)
        })
    }

    private fun goToHome(boolean: Boolean){
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}