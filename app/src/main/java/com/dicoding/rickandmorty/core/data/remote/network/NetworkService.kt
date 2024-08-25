package com.dicoding.rickandmorty.core.data.remote.network

import com.dicoding.rickandmorty.core.data.remote.response.CharacterResponse
import retrofit2.http.GET

interface NetworkService {
    @GET("character")
    suspend fun getListCharacter():CharacterResponse
}