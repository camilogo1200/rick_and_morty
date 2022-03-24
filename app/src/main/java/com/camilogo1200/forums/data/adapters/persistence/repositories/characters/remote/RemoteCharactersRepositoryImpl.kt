package com.camilogo1200.forums.data.adapters.persistence.repositories.characters.remote

import com.camilogo1200.forums.data.adapters.networking.dto.AllCharactersResponse
import com.camilogo1200.forums.data.adapters.networking.services.RickAndMortyApi
import com.camilogo1200.forums.data.adapters.persistence.repositories.characters.CharactersRepository
import com.camilogo1200.forums.domain.models.Character
import com.camilogo1200.forums.utils.coroutines.networkCall
import ironark.com.charge.utils.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoteCharactersRepositoryImpl @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val apiService: RickAndMortyApi
) : CharactersRepository {
    var nextPage: Int? = null
    override suspend fun getAllCharacters(): Result<List<Character>> {
        val result: Result<List<Character>>
        var body: AllCharactersResponse? = null
        val response = networkCall(dispatcher, apiService::getAllCharacters, nextPage)
        result = if (response.isSuccessful) {
            body = response.body()
            body?.let { body ->
                Result.success(body.results.map { it.toModel() })
            } ?: Result.failure(
                Error("Error loading all character from API => ${response.raw().request.url}")
            )
        } else {
            Result.failure(
                Error("Error loading all character from API => ${response.raw().request.url}")
            )
        }
        body?.let {
            nextPage = Integer.parseInt(body.info.next?.split("=")?.get(1) ?: "null")
        }

        return result
    }
}
