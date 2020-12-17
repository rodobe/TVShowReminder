package com.tooldev.test.data.model.response

import com.google.gson.annotations.SerializedName


data class HighlightResult (

	@SerializedName("author") val author : Author,
	@SerializedName("comment_text") val comment_text : Comment_text,
	@SerializedName("story_title") val story_title : Story_title,
	@SerializedName("story_url") val story_url : Story_url
)