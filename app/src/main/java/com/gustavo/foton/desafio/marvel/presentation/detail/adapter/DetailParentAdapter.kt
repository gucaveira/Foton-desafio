package com.gustavo.foton.desafio.marvel.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gustavo.foton.desafio.marvel.databinding.ItemParentDetailBinding
import com.gustavo.foton.desafio.marvel.framework.imageLoader.ImageLoader
import com.gustavo.foton.desafio.marvel.presentation.detail.DetailParentViewEnd

class DetailParentAdapter(
    private val detailParentList: List<DetailParentViewEnd>,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<DetailParentAdapter.DetailParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailParentViewHolder {
        return DetailParentViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: DetailParentViewHolder, position: Int) {
        return holder.bind(detailParentList[position])
    }

    override fun getItemCount(): Int {
        return detailParentList.size
    }

    class DetailParentViewHolder private constructor(
        itemBinding: ItemParentDetailBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val textItemCategory = itemBinding.TextItemCategory
        private val recyclerChildDetail = itemBinding.recyclerChildDetail

        fun bind(detailParentViewEnd: DetailParentViewEnd) {
            textItemCategory.text =
                itemView.context.getText(detailParentViewEnd.categoryStringResId)

            recyclerChildDetail.run {
                setHasFixedSize(true)
                adapter = DetailChildAdapter(detailParentViewEnd.detailChildList, imageLoader)
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader
            ): DetailParentViewHolder {

                val itemBinding = ItemParentDetailBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                return DetailParentViewHolder(itemBinding, imageLoader)
            }
        }
    }
}