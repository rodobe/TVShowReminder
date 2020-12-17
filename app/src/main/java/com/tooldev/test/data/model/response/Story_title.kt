package com.tooldev.test.data.model.response

import com.google.gson.annotations.SerializedName

data class Story_title (

	@SerializedName("value") val value : String,
	@SerializedName("matchLevel") val matchLevel : String,
	@SerializedName("matchedWords") val matchedWords : List<String>
)