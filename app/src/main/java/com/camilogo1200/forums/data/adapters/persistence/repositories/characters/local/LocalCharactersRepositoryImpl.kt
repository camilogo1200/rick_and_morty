package com.camilogo1200.forums.data.adapters.persistence.repositories.characters.local

import com.camilogo1200.forums.data.adapters.persistence.repositories.characters.CharactersRepository
import com.camilogo1200.forums.domain.models.Character
import javax.inject.Inject

class LocalCharactersRepositoryImpl @Inject constructor() : CharactersRepository {
    override suspend fun getAllCharacters(): Result<List<Character>> {
        TODO("Not yet implemented")
    }
}
