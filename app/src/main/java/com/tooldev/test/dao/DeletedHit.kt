package com.tooldev.test.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.tooldev.test.data.model.response.Hit

@Entity(primaryKeys = ["author", "story_id"])
data class DeletedHit (


    @ColumnInfo(name = "author") val author : String,
    @ColumnInfo(name = "story_id") val story_id : Int

){
    constructor(): this("", 0)
}