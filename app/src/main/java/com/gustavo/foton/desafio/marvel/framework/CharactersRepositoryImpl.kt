package com.gustavo.foton.desafio.marvel.framework

import androidx.paging.PagingSource
import com.gustavo.foton.desafio.core.data.repository.CharactersRemoteDataSource
import com.gustavo.foton.desafio.core.data.repository.CharactersRepository
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.core.domain.model.Comic
import com.gustavo.foton.desafio.core.domain.model.Event
import com.gustavo.foton.desafio.marvel.framework.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {
    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }

    override suspend fun getComics(characterId: Int): List<Comic> {
        return remoteDataSource.fetchComics(characterId)
    }

    override suspend fun getEvent(characterId: Int): List<Event> {
        return remoteDataSource.fetchEvents(characterId)
    }
}