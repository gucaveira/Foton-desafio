package com.gustavo.foton.desafio.marvel.presentation.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.core.usecase.GetCharactersUseCase
import com.gustavo.foton.desafio.core.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    coroutineDispatcher: CoroutinesDispatchers
) : ViewModel() {

    var currentSearchQuery = ""

    private val action = MutableLiveData<Action>()
    /* val state: LiveData<UiState> = action.switchMap { action ->
         when (action) {
             is Action.Search, Action.Sort -> {
                 getCharactersUseCase(
                     GetCharactersUseCase.GetCharactersParams(
                         currentSearchQuery, getPageConfig()
                     )
                 ).cachedIn(viewModelScope).map {
                     UiState.SearchResult(it)
                 }.asLiveData(coroutineDispatcher.main())
             }
         }
     }*/

    private fun getPageConfig() = PagingConfig(pageSize = 20)

    fun charactersPagingData(query: String): Flow<PagingData<Character>> {
        return getCharactersUseCase(
            GetCharactersUseCase.GetCharactersParams(query, getPageConfig())
        ).cachedIn(viewModelScope)
    }

    sealed class UiState {
        data class SearchResult(val data: PagingData<Character>) : UiState()
    }

    sealed class Action {
        object Search : Action()
        object Sort : Action()
    }
}