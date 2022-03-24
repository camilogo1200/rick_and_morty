package com.camilogo1200.forums.data.di.modules

import javax.inject.Qualifier


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class LocalCharactersRepository

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RemoteCharactersRepository
