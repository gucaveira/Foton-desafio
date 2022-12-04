package com.gustavo.foton.desafio.marvel.framework.di

import com.gustavo.foton.desafio.core.data.repository.FavoritesRepository
import com.gustavo.foton.desafio.core.repository.FavoritesLocalDataSource
import com.gustavo.foton.desafio.marvel.framework.FavoritesRepositoryImpl
import com.gustavo.foton.desafio.marvel.framework.local.RoomFavoritesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesRepositoryModule {

    @Binds
    fun bindFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    fun bindLocalDataSource(dataSource: RoomFavoritesDataSource): FavoritesLocalDataSource
}