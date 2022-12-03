package com.gustavo.foton.desafio.marvel.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.core.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {

    private fun getPageConfig() = PagingConfig(pageSize = 20)

    fun charactersPagingData(query: String): Flow<PagingData<Character>> {
        return getCharactersUseCase(
            GetCharactersUseCase.GetCharactersParams(query, getPageConfig())
        ).cachedIn(viewModelScope)
    }
}