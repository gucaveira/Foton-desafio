package com.gustavo.foton.desafio.marvel.framework.network.response

import com.google.gson.annotations.SerializedName
import com.gustavo.foton.desafio.core.domain.model.Comic

data class ComicResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun ComicResponse.toComicsModel(): Comic {
    return Comic(
        id = this.id,
        imageUrl = this.thumbnail.getHttpsUrl()
    )
}