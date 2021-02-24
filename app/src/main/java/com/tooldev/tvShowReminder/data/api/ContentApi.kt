package com.tooldev.tvShowReminder.data.api

import com.tooldev.tvShowReminder.data.model.response.home.GenresResult
import com.tooldev.tvShowReminder.data.model.response.home.TvShowsResult
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentApi {

    @GET("tv/on_the_air?api_key=208ca80d1e219453796a7f9792d16776")
    suspend fun getTvShows(@Query("page") page: Int?, @Query("language") language: String = "es-MX"): TvShowsResult

    @GET("tv/{tvId}?api_key=208ca80d1e219453796a7f9792d16776")
    suspend fun getTvShowDetails(@Path("tvId") tvId: Int?, @Query("language") language: String = "es-MX"): TvShowDetails

    @GET("genre/tv/list?api_key=208ca80d1e219453796a7f9792d16776")
    suspend fun getGenres(@Query("language") language: String = "es-MX"): GenresResult

}