package com.gustavo.foton.desafio.marvel.presentation.detail.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.gustavo.foton.desafio.core.usecase.AddFavoriteUseCase
import com.gustavo.foton.desafio.core.usecase.CheckFavoriteUseCase
import com.gustavo.foton.desafio.core.usecase.RemoveFavoriteUseCase
import com.gustavo.foton.desafio.marvel.R
import com.gustavo.foton.desafio.marvel.presentation.detail.DetailViewArg
import com.gustavo.foton.desafio.marvel.presentation.detail.extension.watchStatus
import kotlin.coroutines.CoroutineContext

class FavoriteUiActionStateLiveData(
    private val coroutineContext: CoroutineContext,
    private val checkFavoriteUseCase: CheckFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    removeFavoriteUseCase: RemoveFavoriteUseCase
) {

    @set:VisibleForTesting
    var currentFavoriteIcon = R.drawable.ic_favorite_unchecked

    private val action = MutableLiveData<Action>()
    val state: LiveData<UiState> = action.switchMap {
        liveData(coroutineContext) {
            when (it) {
                is Action.CheckFavorite -> {
                    checkFavoriteUseCase(CheckFavoriteUseCase.Params(it.characterId)).watchStatus(
                        success = { isFavorite ->
                            if (isFavorite) {
                                currentFavoriteIcon = R.drawable.ic_favorite_checked
                            }
                            emitFavoriteIcon()
                        },
                        error = {

                        })
                }
                is Action.AddFavorite -> {
                    it.detailViewArg.run {
                        addFavoriteUseCase(
                            AddFavoriteUseCase.Params(characterId, name, imageUrl)
                        ).watchStatus(
                            loading = {
                                emit(UiState.Loading)
                            }, success = {
                                currentFavoriteIcon = R.drawable.ic_favorite_checked
                                emitFavoriteIcon()
                            }, error = {
                                emit(UiState.Error(R.string.error_add_favorite))
                            })
                    }
                }
                is Action.RemoveFavorite -> {
                    it.detailViewArg.run {
                        removeFavoriteUseCase(
                            RemoveFavoriteUseCase.Params(
                                characterId, name, imageUrl
                            )
                        ).watchStatus(
                            loading = {
                                emit(UiState.Loading)
                            },
                            success = {
                                currentFavoriteIcon = R.drawable.ic_favorite_unchecked
                                emitFavoriteIcon()
                            },
                            error = {
                                emit(UiState.Error(R.string.remove_favorite))
                            }
                        )
                    }
                }
            }
        }

    }

    private suspend fun LiveDataScope<UiState>.emitFavoriteIcon() {
        emit(UiState.Icon(currentFavoriteIcon))
    }

    fun checkFavorite(characterId: Int) {
        action.value = Action.CheckFavorite(characterId)
    }

    fun update(detailViewArg: DetailViewArg) {
        action.value = if (currentFavoriteIcon == R.drawable.ic_favorite_unchecked) {
            Action.AddFavorite(detailViewArg)
        } else Action.RemoveFavorite(detailViewArg)
    }

    sealed class UiState {
        object Loading : UiState()
        data class Icon(@DrawableRes val icon: Int) : UiState()
        data class Error(@StringRes val messageResId: Int) : UiState()
    }

    sealed class Action {
        data class CheckFavorite(val characterId: Int) : Action()
        data class AddFavorite(val detailViewArg: DetailViewArg) : Action()
        data class RemoveFavorite(val detailViewArg: DetailViewArg) : Action()
    }
}