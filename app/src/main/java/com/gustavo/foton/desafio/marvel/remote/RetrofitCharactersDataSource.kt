package com.gustavo.foton.desafio.marvel.remote

import com.gustavo.foton.desafio.core.data.repository.CharactersRemoteDataSource
import com.gustavo.foton.desafio.core.domain.model.CharacterPaging
import com.gustavo.foton.desafio.core.domain.model.Comic
import com.gustavo.foton.desafio.core.domain.model.Event
import com.gustavo.foton.desafio.marvel.framework.network.MarvelApi
import com.gustavo.foton.desafio.marvel.framework.network.response.toCharacterModel
import com.gustavo.foton.desafio.marvel.framework.network.response.toComicsModel
import com.gustavo.foton.desafio.marvel.framework.network.response.toEventModel
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDataSource {

    override suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging {
        val data = marvelApi.getCharacters(queries).data

        val characters = data.results.map {
            it.toCharacterModel()
        }

        return CharacterPaging(data.offset, data.total, characters)
    }

    override suspend fun fetchComics(charactersId: Int): List<Comic> {
        return marvelApi.getComics(charactersId).data.results.map {
            it.toComicsModel()
        }
    }

    override suspend fun fetchEvents(charactersId: Int): List<Event> {
        return marvelApi.getEvents(charactersId).data.results.map {
            it.toEventModel()
        }
    }
}