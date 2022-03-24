package com.camilogo1200.forums.data.adapters.persistence.repositories.characters

import com.camilogo1200.forums.domain.models.Character

interface CharactersRepository {
    suspend fun getAllCharacters(): Result<List<Character>>
}
