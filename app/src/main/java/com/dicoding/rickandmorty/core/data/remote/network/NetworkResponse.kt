package com.dicoding.rickandmorty.core.data.remote.network

sealed class NetworkResponse <out R> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val errorMessage: String) : NetworkResponse<Nothing>()
    data object Empty : NetworkResponse<Nothing>()
}