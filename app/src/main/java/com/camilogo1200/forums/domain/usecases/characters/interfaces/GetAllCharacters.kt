package com.camilogo1200.forums.domain.usecases.characters.interfaces

import com.camilogo1200.forums.domain.models.Character

interface GetAllCharacters {
    suspend fun invoke(): Result<List<Character>>
}
