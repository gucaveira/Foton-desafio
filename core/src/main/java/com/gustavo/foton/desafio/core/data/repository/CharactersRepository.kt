package com.gustavo.foton.desafio.core.data.repository

import androidx.paging.PagingSource
import com.gustavo.foton.desafio.core.domain.model.Character

interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>
}