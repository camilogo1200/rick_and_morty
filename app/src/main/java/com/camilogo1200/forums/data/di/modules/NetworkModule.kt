package com.camilogo1200.forums.data.di.modules

import com.camilogo1200.forums.data.adapters.networking.services.RickAndMortyApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val APPLICATION_JSON_MEDIA_TYPE = "application/json"
    private const val MAIN_BACKEND_URL = "https://rickandmortyapi.com/api/"

    @Provides
    fun providesRickAndMortyService(
        retrofitBuilder: Retrofit.Builder,
    ): RickAndMortyApi {
        val retrofit = retrofitBuilder
            .baseUrl(MAIN_BACKEND_URL)
            .build()
        return retrofit.create(RickAndMortyApi::class.java)
    }

    @Provides
    fun providesRetrofitClient(
        okHttpclient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(MAIN_BACKEND_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpclient)
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        return builder.build()
    }


    private val jsonProperties = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @ExperimentalSerializationApi
    @Provides
    fun providesKotlinxConverterFactory(): Converter.Factory {
        val contentType = APPLICATION_JSON_MEDIA_TYPE.toMediaType()
        return jsonProperties.asConverterFactory(contentType)
    }

    @Provides
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

}
