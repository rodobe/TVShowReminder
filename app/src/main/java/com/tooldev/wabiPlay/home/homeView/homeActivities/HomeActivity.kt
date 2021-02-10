package com.tooldev.wabiPlay.home.homeView.homeActivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tooldev.wabiPlay.home.homeView.homeFragments.HomeFragment
import com.tooldev.wabiPlay.R
import com.tooldev.wabiPlay.home.homeView.homeViewModels.HomeViewModel
import com.tooldev.wabiPlay.util.inTransaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.inTransaction {
            replace(R.id.contentFrame, HomeFragment.getInstance())
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}