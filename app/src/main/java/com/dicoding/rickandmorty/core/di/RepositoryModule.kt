package com.dicoding.rickandmorty.core.di

import com.dicoding.rickandmorty.core.data.repositories.CharacterRepository
import com.dicoding.rickandmorty.core.domain.repository.ICharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(characterRepository: CharacterRepository): ICharacterRepository

}