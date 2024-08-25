package com.dicoding.rickandmorty.core.data.utils

import com.dicoding.rickandmorty.core.data.remote.network.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<ResourceState<ResultType>> = flow {
        emit(ResourceState.Loading())
        if (shouldFetch(loadFromDB().first())) {
            emit(ResourceState.Loading())
            when (val apiResponse = createCall().first()) {
                is NetworkResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { ResourceState.Success(it) })
                }
                is NetworkResponse.Empty -> {
                    emitAll(loadFromDB().map { ResourceState.Success(it) })
                }
                is NetworkResponse.Error -> {
                    onFetchFailed()
                    emit(ResourceState.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { ResourceState.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<NetworkResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<ResourceState<ResultType>> = result
}