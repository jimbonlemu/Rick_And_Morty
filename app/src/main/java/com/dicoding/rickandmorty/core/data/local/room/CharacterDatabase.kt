package com.dicoding.rickandmorty.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.rickandmorty.core.data.local.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}