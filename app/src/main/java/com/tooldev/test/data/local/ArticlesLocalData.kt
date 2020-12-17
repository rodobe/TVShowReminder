package com.tooldev.test.data.local

import com.tooldev.test.dao.AppDao
import com.tooldev.test.dao.DeletedHit
import com.tooldev.test.data.model.response.Hit
import javax.inject.Inject

class ArticlesLocalData @Inject constructor (private val appDao: AppDao) {

    suspend fun saveArticles(hits: List<Hit>){
        appDao.deleteAllHits()
        appDao.insertHitsList(hits)
    }

    suspend fun getArticles(): List<Hit> {
        return  appDao.getHitsList()
    }

    suspend fun deleteArticle(deletedHit: DeletedHit){
        appDao.deleteArticle(deletedHit.author, deletedHit.story_id)
        appDao.insertDeletedArticleIdList(deletedHit)
    }

    suspend fun getDeletedArticle(): List<DeletedHit>{
        return appDao.getDeletedArticle()
    }
}