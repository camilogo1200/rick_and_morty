package com.camilogo1200.forums.data.di.modules

import com.camilogo1200.forums.data.adapters.persistence.repositories.characters.CharactersRepository
import com.camilogo1200.forums.data.adapters.persistence.repositories.characters.CharactersRepositoryImpl
import com.camilogo1200.forums.data.adapters.persistence.repositories.characters.local.LocalCharactersRepositoryImpl
import com.camilogo1200.forums.data.adapters.persistence.repositories.characters.remote.RemoteCharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindsCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    @LocalCharactersRepository
    abstract fun bindsLocalCharacterRepository(repository: LocalCharactersRepositoryImpl): CharactersRepository

    @Binds
    @RemoteCharactersRepository
    abstract fun bindsRemoteCharacterRepository(repository: RemoteCharactersRepositoryImpl): CharactersRepository
}
