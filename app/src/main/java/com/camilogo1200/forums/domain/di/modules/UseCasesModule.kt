package com.camilogo1200.forums.domain.di.modules

import com.camilogo1200.forums.domain.usecases.characters.GetAllCharactersImpl
import com.camilogo1200.forums.domain.usecases.characters.interfaces.GetAllCharacters
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun bindsGetAllCharactersUseCase(useCase: GetAllCharactersImpl): GetAllCharacters

}
