package com.jimbonlemu.rickandmorty.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CharacterItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("species")
	val species: String,

	@field:SerializedName("created")
	val created: String,

	@field:SerializedName("origin")
	val origin: Origin,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("location")
	val location: Location,

	@field:SerializedName("episode")
	val episode: List<String?>,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("status")
	val status: String
)