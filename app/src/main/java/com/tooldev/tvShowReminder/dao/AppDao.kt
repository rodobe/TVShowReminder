package com.tooldev.tvShowReminder.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tooldev.tvShowReminder.data.model.response.home.GenresResult
import com.tooldev.tvShowReminder.data.model.response.home.TvShow
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShowDetails: TvShowDetails?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genresResult: GenresResult)

    @Query("SELECT * FROM tvshowdetails ")
    fun getSavedTvShows(): List<TvShowDetails>?

    @Query("SELECT * FROM genresresult ")
    fun getSavedGenres(): GenresResult?
}