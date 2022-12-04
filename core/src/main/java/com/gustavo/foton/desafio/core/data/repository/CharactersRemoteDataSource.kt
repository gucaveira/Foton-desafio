package com.gustavo.foton.desafio.core.data.repository

import com.gustavo.foton.desafio.core.domain.model.CharacterPaging
import com.gustavo.foton.desafio.core.domain.model.Comic
import com.gustavo.foton.desafio.core.domain.model.Event

interface CharactersRemoteDataSource {
    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging
    suspend fun fetchComics(charactersId: Int): List<Comic>
    suspend fun fetchEvents(charactersId: Int): List<Event>
}