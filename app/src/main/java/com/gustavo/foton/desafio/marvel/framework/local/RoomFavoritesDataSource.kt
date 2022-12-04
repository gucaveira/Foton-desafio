package com.gustavo.foton.desafio.marvel.framework.local

import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.core.repository.FavoritesLocalDataSource
import com.gustavo.foton.desafio.marvel.framework.db.dao.FavoriteDao
import com.gustavo.foton.desafio.marvel.framework.db.entity.FavoriteEntity
import com.gustavo.foton.desafio.marvel.framework.db.entity.toCharacterModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class RoomFavoritesDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoritesLocalDataSource {

    override fun getAll(): Flow<List<Character>> {
        return favoriteDao.loadFavorites().map {
            it.toCharacterModel()
        }
    }

    override suspend fun isFavorite(characterId: Int): Boolean {
        return favoriteDao.hasFavorite(characterId) != null
    }

    override suspend fun save(character: Character) {
        favoriteDao.insertFavorite(character.toFavoriteEntity())
    }

    override suspend fun delete(character: Character) {
        favoriteDao.deleteFavorite(character.toFavoriteEntity())
    }

    private fun Character.toFavoriteEntity() = FavoriteEntity(id, name, imageUrl)
}
