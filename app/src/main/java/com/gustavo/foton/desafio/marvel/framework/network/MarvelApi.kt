package com.gustavo.foton.desafio.marvel.framework.network

import com.gustavo.foton.desafio.marvel.framework.network.response.CharacterResponse
import com.gustavo.foton.desafio.marvel.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap queries: Map<String, String>
    ): DataWrapperResponse<CharacterResponse>

}