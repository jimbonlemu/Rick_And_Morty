package com.dicoding.rickandmorty.core.data.local

import com.dicoding.rickandmorty.core.data.local.entity.CharacterEntity
import com.dicoding.rickandmorty.core.data.local.room.CharacterDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val characterDao: CharacterDao) {

    fun getAllCharacters(): Flow<List<CharacterEntity>> = characterDao.getAllCharacters()

    fun getFavoriteCharacters(): Flow<List<CharacterEntity>> = characterDao.getFavoriteCharacters()
    suspend fun insertCharacter(characterList: List<CharacterEntity>) =
        characterDao.insertFavoriteCharacter(characterList)

    fun setFavoriteCharacters(character: CharacterEntity, newState: Boolean) {
        character.isFavorite = newState
        characterDao.updateFavoriteCharacter(character)
    }

}