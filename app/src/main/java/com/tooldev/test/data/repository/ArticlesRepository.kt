package com.tooldev.test.data.repository

import android.content.Context
import com.tooldev.test.dao.DeletedHit
import com.tooldev.test.data.model.response.Hit
import com.tooldev.test.data.api.Result
import com.tooldev.test.data.local.ArticlesLocalData
import com.tooldev.test.data.remote.ArticlesDataSource
import com.tooldev.test.util.UtilsArticlesHome
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticlesRepository @Inject constructor (@ApplicationContext val context: Context, private val articlesDataSource: ArticlesDataSource,
                                              private val articlesLocalData: ArticlesLocalData){

    suspend fun getArticles(): com.tooldev.test.data.api.Result<List<Hit>> =
            withContext(Dispatchers.IO) {
                if (UtilsArticlesHome.getConnectionType(context)) {
                    val result = articlesDataSource.getArticles()
                    val deletedArticles = articlesLocalData.getDeletedArticle()
                    val filteredHits = result.toMutableList().filter {article -> deletedArticles.find { it.story_id == article.story_id && it.author == article.author } == null }
                    articlesLocalData.saveArticles(filteredHits)
                    Result.Success(filteredHits)
                } else {
                    val result = articlesLocalData.getArticles()
                    Result.Success(result)
                }
            }

    suspend fun deleteArticle(deletedHit: DeletedHit){
        withContext(Dispatchers.IO){
            articlesLocalData.deleteArticle(deletedHit)
        }
    }

}