package com.gustavo.foton.desafio.core.data.repository

import com.gustavo.foton.desafio.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAll(): Flow<List<Character>>
    suspend fun isFavorite(characterId: Int): Boolean
    suspend fun saveFavorite(character: Character)
    suspend fun deleteFavorite(character: Character)
}