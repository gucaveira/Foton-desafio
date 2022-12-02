package com.gustavo.foton.desafio.core.data.repository

import com.gustavo.foton.desafio.core.domain.model.CharacterPaging

interface CharactersRemoteDataSource {
    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging
}