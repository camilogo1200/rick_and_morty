package com.camilogo1200.forums.domain.usecases.characters

import com.camilogo1200.forums.data.adapters.persistence.repositories.characters.CharactersRepository
import com.camilogo1200.forums.domain.models.Character
import com.camilogo1200.forums.domain.usecases.characters.interfaces.GetAllCharacters
import javax.inject.Inject

class GetAllCharactersImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : GetAllCharacters {
    override suspend fun invoke(): Result<List<Character>> {
        //business rules
        return charactersRepository.getAllCharacters()
    }
}
