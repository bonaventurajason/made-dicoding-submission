package com.bonaventurajason.news.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SourceResponse(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
)