package com.example.imagelistapplication.model.save

import android.graphics.Bitmap

interface ImageSavable {
    fun save(bitmap: Bitmap, imageName: String, callback: Callback)

    interface Callback {
        fun saved()
        fun saving(percentage: Int)
        fun failed()
        fun existed()
    }
}