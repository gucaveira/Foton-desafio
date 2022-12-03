package com.gustavo.foton.desafio.marvel.framework.imageLoader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.gustavo.foton.desafio.marvel.R

interface ImageLoader {
    fun load(
        imageView: ImageView, imageUrl: String,
        @DrawableRes placerHolder: Int = R.drawable.ic_img_placeholder,
        @DrawableRes fallback: Int = R.drawable.ic_img_loading_error
    )
}
