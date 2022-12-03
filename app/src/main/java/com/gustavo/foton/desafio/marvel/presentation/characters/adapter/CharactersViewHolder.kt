package com.gustavo.foton.desafio.marvel.presentation.characters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.marvel.databinding.ItemCharacterBinding
import com.gustavo.foton.desafio.marvel.framework.imageLoader.ImageLoader
import com.gustavo.foton.desafio.marvel.util.OnCharacterItemClick

class CharactersViewHolder private constructor(
    itemCharacterBinding: ItemCharacterBinding,
    private val imageLoader: ImageLoader,
    private val onItemClickListener: OnCharacterItemClick
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {

    private val textName = itemCharacterBinding.textName
    private val imageCharacter = itemCharacterBinding.imageCharacter

    fun bind(character: Character) {
        textName.text = character.name
        imageCharacter.transitionName = character.name
        imageLoader.load(imageCharacter, character.imageUrl)

        itemView.setOnClickListener {
            onItemClickListener.invoke(character, imageCharacter)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            imageLoader: ImageLoader,
            onItemClickListener: (character: Character, view: View) -> Unit
        ): CharactersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCharacterBinding.inflate(inflater, parent, false)
            return CharactersViewHolder(itemBinding, imageLoader, onItemClickListener)
        }
    }
}
