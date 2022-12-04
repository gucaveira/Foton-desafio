package com.gustavo.foton.desafio.core.usecase

import com.gustavo.foton.desafio.core.data.repository.FavoritesRepository
import com.gustavo.foton.desafio.core.usecase.base.CoroutinesDispatchers
import com.gustavo.foton.desafio.core.usecase.base.ResultStatus
import com.gustavo.foton.desafio.core.usecase.base.UserCase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface CheckFavoriteUseCase {

    operator fun invoke(params: Params): Flow<ResultStatus<Boolean>>

    data class Params(val characterId: Int)
}

class CheckFavoriteUseCaseImpl @Inject constructor(
    private val repository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : UserCase<CheckFavoriteUseCase.Params, Boolean>(), CheckFavoriteUseCase {
    override suspend fun doWork(params: CheckFavoriteUseCase.Params): ResultStatus<Boolean> {
        return withContext(dispatchers.io()) {
            ResultStatus.Success(repository.isFavorite(params.characterId))
        }
    }
}