package com.gustavo.foton.desafio.marvel.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gustavo.foton.desafio.core.data.DbConstants.REMOTE_KEYS_COLUMN_INFO_OFFSET
import com.gustavo.foton.desafio.core.data.DbConstants.REMOTE_KEYS_TABLE_NAME

@Entity(tableName = REMOTE_KEYS_TABLE_NAME)
data class RemoteKey(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = REMOTE_KEYS_COLUMN_INFO_OFFSET)
    val nextOffset: Int?
)