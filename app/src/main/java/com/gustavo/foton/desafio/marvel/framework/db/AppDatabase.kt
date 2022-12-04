package com.gustavo.foton.desafio.marvel.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gustavo.foton.desafio.marvel.framework.db.dao.CharacterDao
import com.gustavo.foton.desafio.marvel.framework.db.dao.FavoriteDao
import com.gustavo.foton.desafio.marvel.framework.db.dao.RemoteKeyDao
import com.gustavo.foton.desafio.marvel.framework.db.entity.CharacterEntity
import com.gustavo.foton.desafio.marvel.framework.db.entity.FavoriteEntity
import com.gustavo.foton.desafio.marvel.framework.db.entity.RemoteKey

@Database(
    entities = [FavoriteEntity::class, CharacterEntity::class, RemoteKey::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    abstract fun characterDao(): CharacterDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}