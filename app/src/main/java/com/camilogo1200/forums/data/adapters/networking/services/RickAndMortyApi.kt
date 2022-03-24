package com.camilogo1200.forums.data.adapters.networking.services

import com.camilogo1200.forums.data.adapters.networking.dto.AllCharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int?): Response<AllCharactersResponse>
}
