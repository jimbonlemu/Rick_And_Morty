package com.jimbonlemu.rickandmorty.core.di

import com.jimbonlemu.rickandmorty.core.BuildConfig
import com.jimbonlemu.rickandmorty.core.data.remote.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val hostName = "rickandmortyapi.com"
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(CertificatePinner.Builder()
                .add(hostName, "sha256/dAFbai5eSQy4IsN10BR9A33RoO4e8uxDVnRXw0mA9Dw=")
                .add(hostName, "sha256/dAFbai5eSQy4IsN10BR9A33RoO4e8uxDVnRXw0mA9Dw=")
                .build())
            .build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient): NetworkService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(NetworkService::class.java)
    }
}