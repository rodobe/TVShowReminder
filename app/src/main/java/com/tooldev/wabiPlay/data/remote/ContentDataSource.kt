package com.tooldev.wabiPlay.data.remote

import com.tooldev.wabiPlay.data.api.ContentApi
import com.tooldev.wabiPlay.data.model.response.Content
import com.tooldev.wabiPlay.data.model.response.Title
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class ContentDataSource @Inject constructor(private val contentApi: ContentApi) {

    suspend fun getContent(search: String?, type: String?): Content {
        var data: Content? = null
        data = withTimeout(5000) {
            contentApi.getContent(search, type)
        }
        return data
    }

    suspend fun getTitle(title: String?, type: String?, plot: String?): Title {
        var data: Title? = null
        data = withTimeout(5000) {
            contentApi.getTitle(title, type, plot)
        }
        return data
    }

}