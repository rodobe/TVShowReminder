package com.tooldev.wabiPlay.data.model.response

import com.google.gson.annotations.SerializedName

data class Content (

		@SerializedName("Search") val contentResults : List<ContentResult>,
		@SerializedName("totalResults") val totalResults : Int,
		@SerializedName("Response") val response : Boolean
)