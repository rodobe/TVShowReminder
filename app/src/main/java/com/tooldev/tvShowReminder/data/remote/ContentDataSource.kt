package com.tooldev.tvShowReminder.data.remote

import com.tooldev.tvShowReminder.data.api.ContentApi
import com.tooldev.tvShowReminder.data.model.response.home.GenresResult
import com.tooldev.tvShowReminder.data.model.response.home.TvShowsResult
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class ContentDataSource @Inject constructor(private val contentApi: ContentApi) {

    suspend fun getTvShows(page: Int?): TvShowsResult {
        var data: TvShowsResult? = null
        data = withTimeout(5000) {
            contentApi.getTvShows(page)
        }
        return data
    }

    suspend fun getTvShowDetails(tvId: Int): TvShowDetails? {
        var data: TvShowDetails? = null
        data = withTimeout(5000) {
            contentApi.getTvShowDetails(tvId)
        }
        return data
    }

    suspend fun getGenres(): GenresResult {
        var data: GenresResult? = null
        data = withTimeout(5000) {
            contentApi.getGenres()
        }
        return data
    }


}