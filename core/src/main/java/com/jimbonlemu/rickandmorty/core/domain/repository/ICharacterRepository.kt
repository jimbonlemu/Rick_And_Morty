package com.jimbonlemu.rickandmorty.core.domain.repository

import com.jimbonlemu.rickandmorty.core.data.utils.ResourceState
import com.jimbonlemu.rickandmorty.core.domain.model.Character
import kotlinx.coroutines.flow.Flow


interface ICharacterRepository {
    fun getAllCharacters(): Flow<ResourceState<List<Character>>>
    fun searchCharacter(name: String): Flow<ResourceState<List<Character>>>

    fun getFavoriteCharacter():Flow<List<Character>>

    fun setFavoriteCharacter(character: Character, state:Boolean)
}