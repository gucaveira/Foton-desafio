package com.gustavo.foton.desafio.marvel.framework.di

import com.gustavo.foton.desafio.core.usecase.AddFavoriteUseCase
import com.gustavo.foton.desafio.core.usecase.AddFavoriteUseCaseImpl
import com.gustavo.foton.desafio.core.usecase.CheckFavoriteUseCase
import com.gustavo.foton.desafio.core.usecase.CheckFavoriteUseCaseImpl
import com.gustavo.foton.desafio.core.usecase.GetCharacterCategoriesUseCase
import com.gustavo.foton.desafio.core.usecase.GetCharacterCategoriesUseCaseImpl
import com.gustavo.foton.desafio.core.usecase.GetCharactersUseCase
import com.gustavo.foton.desafio.core.usecase.GetCharactersUseCaseImpl
import com.gustavo.foton.desafio.core.usecase.GetFavoritesUseCase
import com.gustavo.foton.desafio.core.usecase.GetFavoritesUseCaseImpl
import com.gustavo.foton.desafio.core.usecase.RemoveFavoriteUseCase
import com.gustavo.foton.desafio.core.usecase.RemoveFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindGetComicsUseCase(useCase: GetCharacterCategoriesUseCaseImpl): GetCharacterCategoriesUseCase

    @Binds
    fun bindAddFavoriteUseCase(useCase: AddFavoriteUseCaseImpl): AddFavoriteUseCase

    @Binds
    fun bindCheckFavoriteUseCase(useCase: CheckFavoriteUseCaseImpl): CheckFavoriteUseCase

    @Binds
    fun bindRemoveFavoriteUseCase(useCase: RemoveFavoriteUseCaseImpl): RemoveFavoriteUseCase

    @Binds
    fun bindGetFavoritesUseCase(useCase: GetFavoritesUseCaseImpl): GetFavoritesUseCase
}