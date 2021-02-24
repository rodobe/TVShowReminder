package com.tooldev.tvShowReminder.data.repository

import android.content.Context
import com.tooldev.tvShowReminder.data.api.Result
import com.tooldev.tvShowReminder.data.local.ContentLocalData
import com.tooldev.tvShowReminder.data.model.response.home.Genre
import com.tooldev.tvShowReminder.data.model.response.home.GenresResult
import com.tooldev.tvShowReminder.data.model.response.home.TvShow
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails
import com.tooldev.tvShowReminder.data.remote.ContentDataSource
import com.tooldev.tvShowReminder.util.connectionType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContentRepository @Inject constructor (@ApplicationContext val context: Context, private val contentLocalData: ContentLocalData, private val contentDataSource: ContentDataSource){

    suspend fun getTvShows(page: Int?): com.tooldev.tvShowReminder.data.api.Result<List<TvShow>?> =
            withContext(Dispatchers.IO) {
                if (context.connectionType()) {
                    val tvShows = contentDataSource.getTvShows(page)
                    val savedTvShows = contentLocalData.getSavedTvShows()
                    val filteredTvShows = tvShows.results?.filter { result -> savedTvShows?.find { it.id == result.id } == null }
                    Result.Success(filteredTvShows)
                } else{
                    Result.Error(Exception("Connection Failure"))
                }
            }

    suspend fun getTvShowDetails(tvId: Int): com.tooldev.tvShowReminder.data.api.Result<TvShowDetails?> =
            withContext(Dispatchers.IO) {
                if (context.connectionType()) {
                    val details = contentDataSource.getTvShowDetails(tvId)
                    Result.Success(details)
                } else{
                    Result.Error(Exception("Connection Failure"))
                }
            }

    suspend fun getGenres(): com.tooldev.tvShowReminder.data.api.Result<GenresResult?> =
            withContext(Dispatchers.IO) {
                if (context.connectionType()) {
                    val genres = contentDataSource.getGenres()
                    contentLocalData.saveGenres(genres)
                    Result.Success(genres)
                } else{
                    Result.Error(Exception("Connection Failure"))
                }
            }

    suspend fun getSubscirbedTvShows(): com.tooldev.tvShowReminder.data.api.Result<List<TvShowDetails>?> =
            withContext(Dispatchers.IO) {
                val savedTvShows = contentLocalData.getSavedTvShows()
                if (savedTvShows != null && savedTvShows.isNotEmpty()) {
                    Result.Success(savedTvShows)
                } else {
                    Result.Error(Exception("No subscribe to any tv show"))
                }
            }

    suspend fun subscirbedTvShows(tvShowDetails: TvShowDetails?) =
            withContext(Dispatchers.IO) {
                contentLocalData.saveTvShow(tvShowDetails)
            }



}