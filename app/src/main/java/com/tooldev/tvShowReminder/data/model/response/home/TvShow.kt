package com.tooldev.tvShowReminder.data.model.response.home

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class TvShow (

	@ColumnInfo(name = "id") val id : Int?,
	val backdrop_path : String?,
	val first_air_date : String?,
	val genre_ids : List<Int>?,
	val name : String?,
	val origin_country : List<String>?,
	val original_language : String?,
	val original_name : String?,
	val overview : String?,
	val popularity : Double?,
	val poster_path : String?,
	val vote_average : Double?,
	val vote_count : Int?
){
	constructor(): this(null, null, null, null,null, null, null, null, null, null, null, null, null)
}