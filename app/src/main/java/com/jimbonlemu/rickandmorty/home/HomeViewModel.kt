package com.jimbonlemu.rickandmorty.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jimbonlemu.rickandmorty.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val characterUseCase: CharacterUseCase) :
    ViewModel() {
    init {
        searchCharacterName("rick")
    }

    val character = characterUseCase.getAllCharacters().asLiveData()
    fun searchCharacterName(name: String) =
        characterUseCase.searchCharacter(name).asLiveData()
}