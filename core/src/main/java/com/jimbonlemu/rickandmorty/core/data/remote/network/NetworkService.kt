package com.jimbonlemu.rickandmorty.core.data.remote.network

import com.jimbonlemu.rickandmorty.core.data.remote.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("character")
    suspend fun getListCharacter():CharacterResponse
    @GET("character/")
    suspend fun searchCharacter(
        @Query("name") name: String
    ):CharacterResponse

}