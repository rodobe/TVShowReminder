package com.tooldev.tvShowReminder.data.model.response.home

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class TvShowsResult (

        @ColumnInfo(name = "page") val page : Int?,
        @ColumnInfo(name = "results") val results : List<TvShow>?,
        @ColumnInfo(name = "total_pages") val total_pages : Int?,
        @ColumnInfo(name = "total_results") val total_results : Int?
){
	constructor(): this(1, null, null, null)
}