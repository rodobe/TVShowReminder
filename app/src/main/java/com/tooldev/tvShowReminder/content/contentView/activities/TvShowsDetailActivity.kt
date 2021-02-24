package com.tooldev.tvShowReminder.content.contentView.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tooldev.tvShowReminder.R
import com.tooldev.tvShowReminder.content.contentView.fragments.TvShowsDetailFragment
import com.tooldev.tvShowReminder.util.inTransaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowsDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_detail)

        supportFragmentManager.inTransaction {
            replace(R.id.content_frame, TvShowsDetailFragment.getInstance(intent.getIntExtra("tvId", 0)))
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}