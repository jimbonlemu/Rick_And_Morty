package com.jimbonlemu.rickandmorty.di
import com.jimbonlemu.rickandmorty.core.domain.usecase.CharacterInteractor
import com.jimbonlemu.rickandmorty.core.domain.usecase.CharacterUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideCharacterUseCase(characterInteractor: CharacterInteractor): CharacterUseCase

}