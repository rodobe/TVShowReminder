package com.tooldev.tvShowReminder.data.model.response.tvShowsDetails

import com.google.gson.annotations.SerializedName

data class SpokenLanguages (

		@SerializedName("english_name") val english_name : String,
		@SerializedName("iso_639_1") val iso_639_1 : String,
		@SerializedName("name") val name : String
)