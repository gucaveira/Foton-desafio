package com.gustavo.foton.desafio.core.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gustavo.foton.desafio.core.data.repository.CharactersRepository
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.core.usecase.GetCharactersUseCase.GetCharactersParams
import com.gustavo.foton.desafio.core.usecase.base.PagingUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetCharactersUseCase {
    operator fun invoke(params: GetCharactersParams): Flow<PagingData<Character>>

    data class GetCharactersParams(val query: String, val pagingConfig: PagingConfig)
}

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : PagingUseCase<GetCharactersParams, Character>(), GetCharactersUseCase {

    override fun createFlowObservable(params: GetCharactersParams): Flow<PagingData<Character>> {
        return charactersRepository.getCachedCharacters(params.query, params.pagingConfig)
    }

}