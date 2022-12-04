package com.gustavo.foton.desafio.marvel.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gustavo.foton.desafio.marvel.databinding.ItemCharacterBinding
import com.gustavo.foton.desafio.marvel.framework.imageLoader.ImageLoader
import com.gustavo.foton.desafio.marvel.presentation.common.GenericViewHolder

class FavoritesViewHolder private constructor(
    itemBinding: ItemCharacterBinding,
    private val imageLoader: ImageLoader
) : GenericViewHolder<FavoriteItem>(itemBinding) {

    private val textName = itemBinding.textName
    private val imageCharacter = itemBinding.imageCharacter

    override fun bind(data: FavoriteItem) {
        textName.text = data.name
        imageLoader.load(imageCharacter, data.imageUrl)
    }

    companion object {
        fun create(parent: ViewGroup, imageLoader: ImageLoader): FavoritesViewHolder {
            val itemBinding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return FavoritesViewHolder(itemBinding, imageLoader)
        }
    }
}
