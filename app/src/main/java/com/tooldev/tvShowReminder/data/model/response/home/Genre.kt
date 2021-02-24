package com.tooldev.tvShowReminder.data.model.response.home

import com.google.gson.annotations.SerializedName

data class Genre (

		@SerializedName("id") val id : Int,
		@SerializedName("name") val name : String
)