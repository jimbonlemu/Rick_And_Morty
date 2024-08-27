package com.jimbonlemu.rickandmorty.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

	@field:SerializedName("results")
	val results: List<CharacterItem>? = null,

	@field:SerializedName("info")
	val info: Info? = null
)