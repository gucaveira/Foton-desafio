package com.gustavo.foton.desafio.marvel.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.gustavo.foton.desafio.core.usecase.AddFavoriteUseCase
import com.gustavo.foton.desafio.core.usecase.CheckFavoriteUseCase
import com.gustavo.foton.desafio.core.usecase.GetCharacterCategoriesUseCase
import com.gustavo.foton.desafio.core.usecase.RemoveFavoriteUseCase
import com.gustavo.foton.desafio.core.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    checkFavoriteUseCase: CheckFavoriteUseCase,
    removeFavoriteUseCase: RemoveFavoriteUseCase,
    coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    val categories = UiActionStateLiveData(
        coroutinesDispatchers.main(), getCharacterCategoriesUseCase
    )

    val favorite = FavoriteUiActionStateLiveData(
        coroutinesDispatchers.main(),
        checkFavoriteUseCase,
        addFavoriteUseCase,
        removeFavoriteUseCase
    )
}