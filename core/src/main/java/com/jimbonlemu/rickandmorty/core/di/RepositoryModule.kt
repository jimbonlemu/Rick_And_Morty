package com.jimbonlemu.rickandmorty.core.di

import com.jimbonlemu.rickandmorty.core.data.repositories.CharacterRepository
import com.jimbonlemu.rickandmorty.core.domain.repository.ICharacterRepository
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