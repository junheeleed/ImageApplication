package com.example.imagelistapplication.model.repository

import android.graphics.Bitmap
import com.example.imagelistapplication.model.save.ImageSavable
import com.example.imagelistapplication.model.scrape.ImageScrapable

interface ImageRepository {
    fun save(bitmap: Bitmap, imageName: String, callback: ImageSavable.Callback)
    fun getImages(callback: ImageScrapable.Callback)
}