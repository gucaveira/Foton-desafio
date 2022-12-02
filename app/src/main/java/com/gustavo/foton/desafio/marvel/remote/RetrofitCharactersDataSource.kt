package com.gustavo.foton.desafio.marvel.remote

import com.gustavo.foton.desafio.core.data.repository.CharactersRemoteDataSource
import com.gustavo.foton.desafio.core.domain.model.CharacterPaging
import com.gustavo.foton.desafio.marvel.framework.network.MarvelApi
import com.gustavo.foton.desafio.marvel.framework.network.response.toCharacterModel
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
}