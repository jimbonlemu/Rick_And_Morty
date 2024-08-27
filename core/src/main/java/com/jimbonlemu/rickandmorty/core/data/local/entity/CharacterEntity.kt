package com.jimbonlemu.rickandmorty.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "origin_name")
    val originName: String,
    @ColumnInfo(name = "char_name")
    val name: String,
    @ColumnInfo(name = "locationName")
    val locationName: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)

