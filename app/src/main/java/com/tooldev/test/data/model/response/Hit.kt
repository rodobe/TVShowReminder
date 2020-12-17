package com.tooldev.test.data.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["author", "story_id"])
data class Hit (

	@ColumnInfo(name = "created_at") val created_at : String?,
	@ColumnInfo(name = "title") val title : String?,
	@ColumnInfo(name = "url") val url : String?,
	@ColumnInfo(name = "author") val author : String,
	@ColumnInfo(name = "story_title") val story_title : String?,
	@ColumnInfo(name = "story_id") val story_id : Int,
	val points : String?,
	val story_text : String?,
	val comment_text : String?,
	val num_comments : String?,
	val story_url : String?,
	val parent_id : Int,
	val created_at_i : Int
){
	constructor(): this(null, null, null, "", null, 0, null, null, null, null, null, 0, 0)
}