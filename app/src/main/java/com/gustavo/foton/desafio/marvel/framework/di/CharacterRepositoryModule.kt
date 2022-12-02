package com.gustavo.foton.desafio.marvel.framework.di

import com.gustavo.foton.desafio.core.data.repository.CharactersRemoteDataSource
import com.gustavo.foton.desafio.core.data.repository.CharactersRepository
import com.gustavo.foton.desafio.marvel.framework.CharactersRepositoryImpl
import com.gustavo.foton.desafio.marvel.remote.RetrofitCharactersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharacterRepositoryModule {

    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitCharactersDataSource): CharactersRemoteDataSource
}