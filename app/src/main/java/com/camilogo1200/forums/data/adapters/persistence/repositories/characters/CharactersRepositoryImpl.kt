package com.camilogo1200.forums.data.adapters.persistence.repositories.characters

import com.camilogo1200.forums.data.adapters.persistence.repositories.characters.CharactersRepository
import com.camilogo1200.forums.data.di.modules.LocalCharactersRepository
import com.camilogo1200.forums.data.di.modules.RemoteCharactersRepository
import com.camilogo1200.forums.domain.models.Character
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    @LocalCharactersRepository val localRepository: CharactersRepository,
    @RemoteCharactersRepository val remoteRepository: CharactersRepository
) : CharactersRepository {

    private val isLocalDatasourcePriority = false //from user settings
    private val isNetworkAvailable = true //replace with proper injected network manager call

    override suspend fun getAllCharacters(): Result<List<Character>> {
        return if (isNetworkAvailable || !isLocalDatasourcePriority) {
            remoteRepository.getAllCharacters()
        } else {
            localRepository.getAllCharacters()
        }
    }
}
