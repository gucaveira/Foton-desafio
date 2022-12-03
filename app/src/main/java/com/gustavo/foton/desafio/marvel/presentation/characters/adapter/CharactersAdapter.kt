package com.gustavo.foton.desafio.marvel.presentation.characters.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.marvel.framework.imageLoader.ImageLoader
import com.gustavo.foton.desafio.marvel.util.OnCharacterItemClick
import javax.inject.Inject

class CharactersAdapter @Inject constructor(
    private val imageLoader: ImageLoader,
    private val onItemClickListener: OnCharacterItemClick
) : PagingDataAdapter<Character, CharactersViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder.create(parent, imageLoader, onItemClickListener)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
}