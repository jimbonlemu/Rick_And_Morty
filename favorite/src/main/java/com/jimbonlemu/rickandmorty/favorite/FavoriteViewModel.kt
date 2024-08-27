package com.jimbonlemu.rickandmorty.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jimbonlemu.rickandmorty.core.domain.usecase.CharacterUseCase

class FavoriteViewModel (characterUseCase: CharacterUseCase) : ViewModel() {
    val character = characterUseCase.getFavoriteCharacter().asLiveData()
}