package com.tooldev.tvShowReminder.data.model.response.tvShowsDetails

import com.google.gson.annotations.SerializedName

data class LastEpisodeToAir (

		@SerializedName("air_date") val air_date : String,
		@SerializedName("episode_number") val episode_number : Int,
		@SerializedName("id") val id : Int,
		@SerializedName("name") val name : String,
		@SerializedName("overview") val overview : String,
		@SerializedName("production_code") val production_code : String,
		@SerializedName("season_number") val season_number : Int,
		@SerializedName("still_path") val still_path : String,
		@SerializedName("vote_average") val vote_average : Double,
		@SerializedName("vote_count") val vote_count : Int
)