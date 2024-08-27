package com.jimbonlemu.rickandmorty.di

import com.jimbonlemu.rickandmorty.core.domain.usecase.CharacterUseCase

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun provideFavoriteUseCase(): CharacterUseCase
}