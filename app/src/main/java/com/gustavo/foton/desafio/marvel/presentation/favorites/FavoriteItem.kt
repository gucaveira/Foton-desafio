package com.gustavo.foton.desafio.marvel.presentation.favorites

import com.gustavo.foton.desafio.marvel.presentation.common.ListItem

data class FavoriteItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    override val key: Long = id.toLong()
) : ListItem
