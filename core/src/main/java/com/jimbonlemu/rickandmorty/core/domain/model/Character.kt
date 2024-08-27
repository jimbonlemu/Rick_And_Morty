package com.jimbonlemu.rickandmorty.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: String,
    val image: String,
    val gender: String,
    val species: String,
    val originName: String,
    val name: String,
    val locationName: String,
    val type: String,
    val status: String,
    var isFavorite: Boolean,
):Parcelable