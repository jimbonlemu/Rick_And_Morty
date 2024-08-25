package com.dicoding.rickandmorty.core.data.repositories

import com.dicoding.rickandmorty.core.data.local.LocalDataSource
import com.dicoding.rickandmorty.core.data.remote.RemoteDataSource
import com.dicoding.rickandmorty.core.data.remote.network.NetworkResponse
import com.dicoding.rickandmorty.core.data.remote.response.CharacterItem
import com.dicoding.rickandmorty.core.data.utils.NetworkBoundResource
import com.dicoding.rickandmorty.core.data.utils.ResourceState
import com.dicoding.rickandmorty.core.domain.model.Character
import com.dicoding.rickandmorty.core.domain.repository.ICharacterRepository
import com.dicoding.rickandmorty.core.utils.AppExecutors
import com.dicoding.rickandmorty.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : ICharacterRepository {
    override fun getAllCharacters(): Flow<ResourceState<List<Character>>> {
        return object : NetworkBoundResource<List<Character>, List<CharacterItem>>() {
            override fun loadFromDB(): Flow<List<Character>> {
                return localDataSource.getAllCharacters().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<CharacterItem>>> =
                remoteDataSource.getAllCharacters()

            override suspend fun saveCallResult(data: List<CharacterItem>) {
                localDataSource.insertCharacter(DataMapper.mapResponsesToEntities(data))
            }

            override fun shouldFetch(data: List<Character>?): Boolean = true

        }.asFlow()
    }


    override fun getFavoriteCharacter(): Flow<List<Character>> {
        return localDataSource.getFavoriteCharacters().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteCharacter(character: Character, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteCharacters(
                DataMapper.mapDomainToEntity(character),
                state
            )
        }
    }

}