package com.issen.simpleandroid.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders


@BindingAdapter("thumbnail")
fun setThumbnail(view: ImageView, thumbnail: String) {
    val url = GlideUrl(
        thumbnail,
        LazyHeaders.Builder().addHeader("User-Agent", "your-user-agent").build()
    )
    Glide.with(view.context).load(url).into(view)
}