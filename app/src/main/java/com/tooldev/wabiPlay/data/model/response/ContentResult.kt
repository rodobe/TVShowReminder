package com.tooldev.wabiPlay.data.model.response

import com.google.gson.annotations.SerializedName

data class ContentResult (

	@SerializedName("Title") val title : String,
	@SerializedName("Year") val year : String,
	@SerializedName("imdbID") val imdbID : String,
	@SerializedName("Type") val type : String,
	@SerializedName("Poster") val poster : String
)