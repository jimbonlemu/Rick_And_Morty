package com.jimbonlemu.rickandmorty.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class Origin(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String? = null
)