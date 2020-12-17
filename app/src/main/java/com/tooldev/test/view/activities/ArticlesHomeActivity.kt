package com.tooldev.test.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.tooldev.test.view.fragments.ArticlesHomeFragment
import com.tooldev.test.R
import com.tooldev.test.util.inTransaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        supportFragmentManager.inTransaction {
            add(R.id.contentFrame, ArticlesHomeFragment())
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStackImmediate()
        return super.onSupportNavigateUp()
    }

}