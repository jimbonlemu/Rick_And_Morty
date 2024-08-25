package com.dicoding.rickandmorty.core.data.remote

import android.util.Log
import com.dicoding.rickandmorty.core.data.remote.network.NetworkResponse
import com.dicoding.rickandmorty.core.data.remote.network.NetworkService
import com.dicoding.rickandmorty.core.data.remote.response.CharacterItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val networkService: NetworkService) {

    suspend fun getAllCharacters(): Flow<NetworkResponse<List<CharacterItem>>> {
        return flow {
            try {
                val response = networkService.getListCharacter().results

                if (response!= null){
                    emit(NetworkResponse.Success(response))
                } else {
                    emit(NetworkResponse.Empty)
                }
            } catch (e : Exception){
                emit(NetworkResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}