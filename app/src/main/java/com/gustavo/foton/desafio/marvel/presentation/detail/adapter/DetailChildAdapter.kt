package com.gustavo.foton.desafio.marvel.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gustavo.foton.desafio.marvel.databinding.ItemChildDetailBinding
import com.gustavo.foton.desafio.marvel.framework.imageLoader.ImageLoader
import com.gustavo.foton.desafio.marvel.presentation.detail.DetailChildViewEnd

class DetailChildAdapter(
    private val detailChildList: List<DetailChildViewEnd>,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<DetailChildAdapter.DetailChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailChildViewHolder {
        return DetailChildViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: DetailChildViewHolder, position: Int) {
        return holder.bind(detailChildList[position])
    }

    override fun getItemCount() = detailChildList.size

    class DetailChildViewHolder private constructor(
        itemBinding: ItemChildDetailBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val imageCategory = itemBinding.imageItemCategory

        fun bind(detailChildViewEnd: DetailChildViewEnd) {
            imageLoader.load(
                imageCategory,
                detailChildViewEnd.imageUrl
            )
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader
            ): DetailChildViewHolder {

                val itemBinding = ItemChildDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return DetailChildViewHolder(itemBinding, imageLoader)
            }
        }
    }
}