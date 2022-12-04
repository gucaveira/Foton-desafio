package com.gustavo.foton.desafio.marvel.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gustavo.foton.desafio.core.data.DbConstants.REMOTE_KEYS_TABLE_NAME
import com.gustavo.foton.desafio.marvel.framework.db.entity.RemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM $REMOTE_KEYS_TABLE_NAME")
    suspend fun remoteKey(): RemoteKey

    @Query("DELETE FROM $REMOTE_KEYS_TABLE_NAME")
    suspend fun clearAll()
}