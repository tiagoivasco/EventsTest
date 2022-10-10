package com.ivasco.eventstest.ui.glide

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ivasco.eventstest.R

class ImgLoader(
    private val glide: RequestManager,
) {
    fun loadImage(
        imgUrl: String,
        imgView: ImageView,
        imgError: Int = R.drawable.error_image
    ){
        glide
            .load(imgUrl)
            .error(imgError)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(imgView)
    }
}