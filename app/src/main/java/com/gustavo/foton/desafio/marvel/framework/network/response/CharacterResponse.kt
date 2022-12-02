package com.gustavo.foton.desafio.marvel.framework.network.response

import com.google.gson.annotations.SerializedName
import com.gustavo.foton.desafio.core.domain.model.Character

data class CharacterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun CharacterResponse.toCharacterModel(): Character {
    return Character(
        id = id,
        name = this.name,
        imageUrl = this.thumbnail.getHttpsUrl()
    )
}