package com.gustavo.foton.desafio.core.usecase

import com.gustavo.foton.desafio.core.data.repository.FavoritesRepository
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.core.usecase.base.CoroutinesDispatchers
import com.gustavo.foton.desafio.core.usecase.base.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface GetFavoritesUseCase {
    suspend operator fun invoke(params: Unit = Unit): Flow<List<Character>>
}

class GetFavoritesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : FlowUseCase<Unit, List<Character>>(), GetFavoritesUseCase {

    override suspend fun createFlowObservable(params: Unit): Flow<List<Character>> {
        return withContext(dispatchers.io()) {
            favoritesRepository.getAll()
        }
    }
}
