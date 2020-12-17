package com.tooldev.test.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tooldev.test.data.model.response.Hit

@Dao
interface AppDao {

    @Query("SELECT * FROM hit")
    fun getHitsList(): List<Hit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHitsList(hits: List<Hit>)

    @Query("DELETE FROM hit")
    fun deleteAllHits()

    @Query("DELETE FROM hit WHERE author = :author AND story_id = :storyId")
    fun deleteArticle(author: String, storyId: Int)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeletedArticleIdList(deletedHit: DeletedHit)

    @Query("DELETE FROM deletedhit")
    fun deleteAllDeletedArticleid()

    @Query("SELECT * FROM deletedhit ")
    fun getDeletedArticle(): List<DeletedHit>
}