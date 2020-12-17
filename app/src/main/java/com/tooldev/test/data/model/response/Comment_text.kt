package com.tooldev.test.data.model.response

import com.google.gson.annotations.SerializedName

data class Comment_text (

	@SerializedName("value") val value : String,
	@SerializedName("matchLevel") val matchLevel : String,
	@SerializedName("fullyHighlighted") val fullyHighlighted : Boolean,
	@SerializedName("matchedWords") val matchedWords : List<String>
)