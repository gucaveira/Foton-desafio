package com.gustavo.foton.desafio.marvel.framework.network.response

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse<T>(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val data: DataContainerResponse<T>
)