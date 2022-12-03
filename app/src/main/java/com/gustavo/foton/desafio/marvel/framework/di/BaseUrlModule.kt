package com.gustavo.foton.desafio.marvel.framework.di

import com.gustavo.foton.desafio.marvel.BuildConfig
import com.gustavo.foton.desafio.marvel.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @BaseUrl
    @Provides
    fun providesBaseUrl(): String = BuildConfig.BASE_URL
}