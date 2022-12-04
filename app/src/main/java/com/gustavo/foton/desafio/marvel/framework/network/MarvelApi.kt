package com.gustavo.foton.desafio.marvel.framework.network

import com.gustavo.foton.desafio.marvel.framework.network.response.CharacterResponse
import com.gustavo.foton.desafio.marvel.framework.network.response.ComicResponse
import com.gustavo.foton.desafio.marvel.framework.network.response.DataWrapperResponse
import com.gustavo.foton.desafio.marvel.framework.network.response.EventResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap queries: Map<String, String>
    ): DataWrapperResponse<CharacterResponse>

    @GET("characters/{charactersId}/comics")
    suspend fun getComics(
        @Path("charactersId") charactersId: Int,
    ): DataWrapperResponse<ComicResponse>

    @GET("characters/{charactersId}/events")
    suspend fun getEvents(
        @Path("charactersId") charactersId: Int,
    ): DataWrapperResponse<EventResponse>
}