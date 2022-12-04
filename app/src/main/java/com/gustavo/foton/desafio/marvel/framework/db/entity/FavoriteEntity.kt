package com.gustavo.foton.desafio.marvel.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gustavo.foton.desafio.core.data.DbConstants.FAVORITES_COLUMN_INFO_ID
import com.gustavo.foton.desafio.core.data.DbConstants.FAVORITES_COLUMN_INFO_IMAGE_URL
import com.gustavo.foton.desafio.core.data.DbConstants.FAVORITES_COLUMN_INFO_NAME
import com.gustavo.foton.desafio.core.data.DbConstants.FAVORITES_TABLE_NAME
import com.gustavo.foton.desafio.core.domain.model.Character

@Entity(tableName = FAVORITES_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_NAME)
    val name: String,
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_IMAGE_URL)
    val imageUrl: String
)

fun List<FavoriteEntity>.toCharacterModel() = map {
    Character(it.id, it.name, it.imageUrl)
}