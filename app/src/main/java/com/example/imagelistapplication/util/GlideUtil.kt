package com.example.imagelistapplication.util

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.imagelistapplication.R
import java.lang.Exception



interface ImageSetCallBack {
    fun callback(isSet: Boolean)
}

fun ImageView.fromUrl(url: String, imageSetCallBack: ImageSetCallBack) {
    Glide.with(this.context)
            .load(url)
            .asBitmap()
            .listener(object : RequestListener<String, Bitmap> {
                override fun onResourceReady(resource: Bitmap?, model: String?, target: Target<Bitmap>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                    imageSetCallBack.callback(true)
                    return false
                }

                override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                    imageSetCallBack.callback(false)
                    return false
                }
            })
            .into(this)
}

fun ImageView.fromUrl(url: String, width: Int, height: Int) {
    Glide.with(this.context)
            .load(url)
            .override(width, height)
            .fitCenter()
            .placeholder(R.mipmap.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
}