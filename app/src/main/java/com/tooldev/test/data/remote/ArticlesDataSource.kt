package com.tooldev.test.data.remote

import com.tooldev.test.data.api.ArticlesApi
import com.tooldev.test.data.model.response.Data
import com.tooldev.test.data.model.response.Hit
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class ArticlesDataSource @Inject constructor(private val articlesApi: ArticlesApi) {

    suspend fun getArticles(): List<Hit> {
        var data: Data? = null
        data = withTimeout(5000) {
            articlesApi.getArticles()
        }
        return data.hits
    }

}