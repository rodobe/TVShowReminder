package com.tooldev.test.data.api

import com.tooldev.test.data.model.response.Data
import retrofit2.http.GET

interface ArticlesApi {

    @GET("search_by_date?query=android")
    suspend fun getArticles(): Data


}