package com.gustavo.foton.desafio.marvel.framework

import com.gustavo.foton.desafio.core.data.repository.FavoritesRepository
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.core.repository.FavoritesLocalDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesLocalDataSource: FavoritesLocalDataSource
) : FavoritesRepository {
    override fun getAll(): Flow<List<Character>> {
        return favoritesLocalDataSource.getAll()
    }

    override suspend fun isFavorite(characterId: Int): Boolean {
        return favoritesLocalDataSource.isFavorite(characterId)
    }

    override suspend fun saveFavorite(character: Character) {
        favoritesLocalDataSource.save(character)
    }

    override suspend fun deleteFavorite(character: Character) {
        favoritesLocalDataSource.delete(character)
    }
}