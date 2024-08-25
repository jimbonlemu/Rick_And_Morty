package com.dicoding.rickandmorty.core.domain.repository

import com.dicoding.rickandmorty.core.data.utils.ResourceState
import com.dicoding.rickandmorty.core.domain.model.Character
import kotlinx.coroutines.flow.Flow


interface ICharacterRepository {

    fun getAllCharacters(): Flow<ResourceState<List<Character>>>

    fun getFavoriteCharacter():Flow<List<Character>>

    fun setFavoriteCharacter(character: Character, state:Boolean)
}