package com.gustavo.foton.desafio.marvel.framework

import androidx.paging.PagingSource
import com.gustavo.foton.desafio.core.data.repository.CharactersRemoteDataSource
import com.gustavo.foton.desafio.core.data.repository.CharactersRepository
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.marvel.framework.paging.CharactersPagingSource

class CharactersRepositoryImpl(private val remoteDataSource: CharactersRemoteDataSource) :
    CharactersRepository {
    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }
}