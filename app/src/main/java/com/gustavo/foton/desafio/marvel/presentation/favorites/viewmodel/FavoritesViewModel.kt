package com.gustavo.foton.desafio.marvel.presentation.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.gustavo.foton.desafio.core.usecase.GetFavoritesUseCase
import com.gustavo.foton.desafio.core.usecase.base.CoroutinesDispatchers
import com.gustavo.foton.desafio.marvel.presentation.favorites.FavoriteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.catch

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val action = MutableLiveData<Action>()

    val state: LiveData<UiState> = action.switchMap { action ->
        liveData(coroutinesDispatchers.main()) {
            when (action) {
                is Action.GetAll -> {
                    getFavoritesUseCase().catch {
                        emit(UiState.ShowEmpty)
                    }.collect {
                        val items = it.map { character ->
                            FavoriteItem(character.id, character.name, character.imageUrl)
                        }

                        val uiState = if (items.isEmpty()) {
                            UiState.ShowEmpty
                        } else UiState.ShowFavorite(items)
                        emit(uiState)
                    }
                }
            }
        }
    }

    fun getAll() {
        action.value = Action.GetAll
    }

    sealed class UiState {
        data class ShowFavorite(val favorites: List<FavoriteItem>) : UiState()
        object ShowEmpty : UiState()
    }

    sealed class Action {
        object GetAll : Action()
    }
}
