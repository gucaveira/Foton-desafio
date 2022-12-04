package com.gustavo.foton.desafio.marvel.framework

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.gustavo.foton.desafio.core.data.repository.CharactersRemoteDataSource
import com.gustavo.foton.desafio.core.data.repository.CharactersRepository
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.core.domain.model.Comic
import com.gustavo.foton.desafio.core.domain.model.Event
import com.gustavo.foton.desafio.marvel.framework.db.AppDatabase
import com.gustavo.foton.desafio.marvel.framework.paging.CharactersPagingSource
import com.gustavo.foton.desafio.marvel.framework.paging.CharactersRemoteMediator
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource, private val database: AppDatabase
) : CharactersRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }

    override fun getCachedCharacters(
        query: String, pagingConfig: PagingConfig
    ): Flow<PagingData<Character>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = CharactersRemoteMediator(query, database, remoteDataSource)
        ) {
            database.characterDao().pagingSource()
        }.flow.map { pagingData ->
            pagingData.map {
                Character(it.id, it.name, it.imageUrl)
            }
        }
    }

    override suspend fun getComics(characterId: Int): List<Comic> {
        return remoteDataSource.fetchComics(characterId)
    }

    override suspend fun getEvent(characterId: Int): List<Event> {
        return remoteDataSource.fetchEvents(characterId)
    }
}