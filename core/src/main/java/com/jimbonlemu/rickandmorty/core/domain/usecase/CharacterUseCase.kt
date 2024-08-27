package com.jimbonlemu.rickandmorty.core.domain.usecase

import com.jimbonlemu.rickandmorty.core.data.utils.ResourceState
import com.jimbonlemu.rickandmorty.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterUseCase{
    fun getAllCharacters():Flow<ResourceState<List<Character>>>
    fun getFavoriteCharacter():Flow<List<Character>>
    fun setFavoriteCharacter(character: Character, state:Boolean)
}