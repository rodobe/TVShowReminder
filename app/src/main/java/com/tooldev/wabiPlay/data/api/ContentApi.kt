package com.tooldev.wabiPlay.data.api

import com.tooldev.wabiPlay.data.model.response.Content
import com.tooldev.wabiPlay.data.model.response.Title
import retrofit2.http.POST
import retrofit2.http.Query

interface ContentApi {

    @POST("?i=tt3896198&apikey=ca7a3047")
    suspend fun getContent(@Query("s") search: String?, @Query ("type") type: String?): Content

    @POST("?i=tt3896198&apikey=ca7a3047")
    suspend fun getTitle(@Query("t") title: String?, @Query ("type") type: String?, @Query ("plot") plot: String?): Title


}