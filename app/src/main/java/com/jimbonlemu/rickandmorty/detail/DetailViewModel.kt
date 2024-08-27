package com.jimbonlemu.rickandmorty.detail

import androidx.lifecycle.ViewModel
import com.jimbonlemu.rickandmorty.core.domain.model.Character
import com.jimbonlemu.rickandmorty.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val characterUseCase: CharacterUseCase) :ViewModel(){
    fun setFavoriteCharacter(character: Character, newStatus:Boolean) =
        characterUseCase.setFavoriteCharacter(character, newStatus)
}