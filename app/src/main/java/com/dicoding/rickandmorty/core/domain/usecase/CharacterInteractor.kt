package com.dicoding.rickandmorty.core.domain.usecase

import com.dicoding.rickandmorty.core.data.utils.ResourceState
import com.dicoding.rickandmorty.core.domain.model.Character
import com.dicoding.rickandmorty.core.domain.repository.ICharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterInteractor @Inject constructor(private val characterRepository: ICharacterRepository) :
    CharacterUseCase {
    override fun getAllCharacters(): Flow<ResourceState<List<Character>>> =
        characterRepository.getAllCharacters()

    override fun getFavoriteCharacter(): Flow<List<Character>> =
        characterRepository.getFavoriteCharacter()

    override fun setFavoriteCharacter(character: Character, state: Boolean) =
        characterRepository.setFavoriteCharacter(character, state)


}