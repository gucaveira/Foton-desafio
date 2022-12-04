package com.gustavo.foton.desafio.marvel.framework.network.response

import com.google.gson.annotations.SerializedName
import com.gustavo.foton.desafio.core.domain.model.Event

data class EventResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)


fun EventResponse.toEventModel(): Event {
    return Event(
        id = this.id,
        imageUrl = this.thumbnail.getHttpsUrl()
    )
}