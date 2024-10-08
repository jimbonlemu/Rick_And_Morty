package com.jimbonlemu.rickandmorty.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jimbonlemu.rickandmorty.core.domain.usecase.CharacterUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val characterUseCase: CharacterUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(characterUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}