package com.tooldev.tvShowReminder.data.model.response.home

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenresResult (

		@PrimaryKey(autoGenerate = true) val id: Int = 0,
		@ColumnInfo(name = "genres") val genres : List<Genre>
)