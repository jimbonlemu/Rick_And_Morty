package com.dicoding.rickandmorty.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.rickandmorty.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(characterUseCase: CharacterUseCase) : ViewModel() {
    val character = characterUseCase.getAllCharacters().asLiveData()
}