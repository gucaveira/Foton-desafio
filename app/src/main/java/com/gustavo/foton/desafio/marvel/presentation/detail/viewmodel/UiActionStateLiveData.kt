package com.gustavo.foton.desafio.marvel.presentation.detail.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.gustavo.foton.desafio.core.usecase.GetCharacterCategoriesUseCase
import com.gustavo.foton.desafio.marvel.R
import com.gustavo.foton.desafio.marvel.presentation.detail.DetailChildViewEnd
import com.gustavo.foton.desafio.marvel.presentation.detail.DetailParentViewEnd
import com.gustavo.foton.desafio.marvel.presentation.detail.extension.watchStatus
import kotlin.coroutines.CoroutineContext

class UiActionStateLiveData(
    private val coroutineContext: CoroutineContext,
    private val getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase
) {

    private val action = MutableLiveData<Action>()


    val state: LiveData<UiState> = action.switchMap {
        liveData(coroutineContext) {
            when (it) {
                is Action.Load -> {
                    getCharacterCategoriesUseCase(
                        GetCharacterCategoriesUseCase.GetCategoriesParams(it.characterId)
                    ).watchStatus(
                        loading = { emit(UiState.Loading) },
                        success = { data ->

                            val detailParentList = mutableListOf<DetailParentViewEnd>()
                            val comics = data.first
                            val events = data.second

                            if (comics.isNotEmpty()) {
                                comics.map { DetailChildViewEnd(it.id, it.imageUrl) }
                                    .also {
                                        detailParentList.add(
                                            DetailParentViewEnd(
                                                R.string.details_comics_category,
                                                it
                                            )
                                        )
                                    }
                            }

                            if (events.isNotEmpty()) {
                                events.map { DetailChildViewEnd(it.id, it.imageUrl) }
                                    .also {
                                        detailParentList.add(
                                            DetailParentViewEnd(
                                                R.string.details_events_category,
                                                it
                                            )
                                        )
                                    }
                            }

                            if (detailParentList.isNotEmpty()) {
                                emit(UiState.Success(detailParentList))
                            } else emit(UiState.Empty)

                        },
                        error = {
                            emit(UiState.Error)
                        }
                    )
                }
            }
        }
    }

    fun load(characterId: Int) {
        action.value = Action.Load(characterId)
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val detailParentList: List<DetailParentViewEnd>) : UiState()
        object Error : UiState()
        object Empty : UiState()
    }

    sealed class Action {
        data class Load(val characterId: Int) : Action()
    }
}