package com.gustavo.foton.desafio.marvel.framework.imageLoader

import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

class GliderImageLoader @Inject constructor() : ImageLoader {
    override fun load(imageView: ImageView, imageUrl: String, placerHolder: Int, fallback: Int) {
        Glide.with(imageView.rootView)
            .load(imageUrl)
            .placeholder(placerHolder)
            .fallback(fallback)
            .into(imageView)
    }
}