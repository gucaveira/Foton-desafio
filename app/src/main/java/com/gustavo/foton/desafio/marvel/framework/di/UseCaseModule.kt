package com.gustavo.foton.desafio.marvel.framework.di

import com.gustavo.foton.desafio.core.usecase.GetCharactersUseCase
import com.gustavo.foton.desafio.core.usecase.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase
}