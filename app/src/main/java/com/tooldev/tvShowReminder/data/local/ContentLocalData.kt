package com.tooldev.tvShowReminder.data.local

import com.tooldev.tvShowReminder.dao.AppDao
import com.tooldev.tvShowReminder.data.model.response.home.GenresResult
import com.tooldev.tvShowReminder.data.model.response.home.TvShow
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails
import javax.inject.Inject

class ContentLocalData @Inject constructor (private val appDao: AppDao) {

    suspend fun saveTvShow(tvShowDetails: TvShowDetails?){
        appDao.insertTvShow(tvShowDetails)
    }

    suspend fun saveGenres(genresResult: GenresResult){
        appDao.insertGenres(genresResult)
    }

    suspend fun getSavedTvShows(): List<TvShowDetails>? {
        return  appDao.getSavedTvShows()
    }

    suspend fun getSavedGenres(): GenresResult? {
        return  appDao.getSavedGenres()
    }
}