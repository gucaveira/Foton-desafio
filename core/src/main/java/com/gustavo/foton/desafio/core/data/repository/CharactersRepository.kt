package com.gustavo.foton.desafio.core.data.repository

import androidx.paging.PagingSource
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.core.domain.model.Comic
import com.gustavo.foton.desafio.core.domain.model.Event

interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>
    suspend fun getComics(characterId: Int): List<Comic>
    suspend fun getEvent(characterId: Int): List<Event>
}