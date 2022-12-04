package com.gustavo.foton.desafio.marvel.framework.di

import com.gustavo.foton.desafio.core.usecase.base.AppCoroutinesDispatchers
import com.gustavo.foton.desafio.core.usecase.base.CoroutinesDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Binds
    fun bindDispatchers(dispatchers: AppCoroutinesDispatchers): CoroutinesDispatchers
}