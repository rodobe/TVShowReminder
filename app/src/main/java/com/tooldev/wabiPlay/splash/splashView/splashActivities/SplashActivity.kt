package com.tooldev.wabiPlay.splash.splashView.splashActivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tooldev.wabiPlay.R
import com.tooldev.wabiPlay.splash.splashView.splashFragments.SplashFragment
import com.tooldev.wabiPlay.util.inTransaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportFragmentManager.inTransaction {
            add(R.id.contentFrame, SplashFragment())
        }

    }

}