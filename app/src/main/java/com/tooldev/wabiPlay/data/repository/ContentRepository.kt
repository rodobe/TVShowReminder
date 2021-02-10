package com.tooldev.wabiPlay.data.repository

import android.content.Context
import com.tooldev.wabiPlay.data.api.Result
import com.tooldev.wabiPlay.data.model.response.Content
import com.tooldev.wabiPlay.data.model.response.Title
import com.tooldev.wabiPlay.data.remote.ContentDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContentRepository @Inject constructor (@ApplicationContext val context: Context, private val contentDataSource: ContentDataSource){

    suspend fun getContent(search: String?, type: String?): com.tooldev.wabiPlay.data.api.Result<Content> =
            withContext(Dispatchers.IO) {
                val result = contentDataSource.getContent(search, type)
                Result.Success(result)
            }

    suspend fun getTitle(title: String?, type: String?, plot: String?): com.tooldev.wabiPlay.data.api.Result<Title> =
            withContext(Dispatchers.IO) {
                val result = contentDataSource.getTitle(title, type, plot)
                Result.Success(result)
            }

}