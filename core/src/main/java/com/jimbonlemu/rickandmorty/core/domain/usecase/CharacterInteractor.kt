package com.jimbonlemu.rickandmorty.core.domain.usecase

import com.jimbonlemu.rickandmorty.core.data.utils.ResourceState
import com.jimbonlemu.rickandmorty.core.domain.model.Character
import com.jimbonlemu.rickandmorty.core.domain.repository.ICharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterInteractor @Inject constructor(private val characterRepository: ICharacterRepository) :
    CharacterUseCase {
    override fun getAllCharacters(): Flow<ResourceState<List<Character>>> =
        characterRepository.getAllCharacters()

    override fun searchCharacter(name: String): Flow<ResourceState<List<Character>>> =
        characterRepository.searchCharacter(name)

    override fun getFavoriteCharacter(): Flow<List<Character>> =
        characterRepository.getFavoriteCharacter()

    override fun setFavoriteCharacter(character: Character, state: Boolean) =
        characterRepository.setFavoriteCharacter(character, state)


}