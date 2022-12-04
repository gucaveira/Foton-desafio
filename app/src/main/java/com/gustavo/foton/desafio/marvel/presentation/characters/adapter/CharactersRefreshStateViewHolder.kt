package com.gustavo.foton.desafio.marvel.presentation.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.gustavo.foton.desafio.marvel.databinding.ItemCharacterRefreshStateBinding

class CharactersRefreshStateViewHolder private constructor(
    itemBinding: ItemCharacterRefreshStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    private var binding = ItemCharacterRefreshStateBinding.bind(itemView)
    private var progressBarLoadingMore = binding.progressRefresh
    private var textTryAgainMessage = binding.textTryAgain.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        progressBarLoadingMore.isVisible = loadState is LoadState.Loading
        textTryAgainMessage.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): CharactersRefreshStateViewHolder {
            val itemBinding = ItemCharacterRefreshStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return CharactersRefreshStateViewHolder(itemBinding, retry)
        }
    }
}