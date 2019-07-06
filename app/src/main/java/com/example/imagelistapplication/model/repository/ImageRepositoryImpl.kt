package com.example.imagelistapplication.model.repository

import android.graphics.Bitmap
import com.example.imagelistapplication.model.save.ImageSavable
import com.example.imagelistapplication.model.scrape.ImageScrapable
import com.example.imagelistapplication.util.ThreadExecutor
import com.example.imagelistapplication.util.isMediaMounted

class ImageRepositoryImpl(private val imageSavable: ImageSavable,
                          private val imageScrapable: ImageScrapable): ImageRepository {

    override fun save(bitmap: Bitmap, imageName: String, callback: ImageSavable.Callback) {
        ThreadExecutor.executeToSingleThread(object : Runnable {
            override fun run() {
                if (!isMediaMounted()) {
                    callback.failed()
                    return
                }
                imageSavable.save(bitmap, imageName, callback)
            }
        })
    }

    override fun getImages(callback: ImageScrapable.Callback) {
        ThreadExecutor.executeToSingleThread(object : Runnable {
            override fun run() {
                val images = imageScrapable.getImages()
                if (images.size > 0) {
                    callback.succeed(images)
                } else {
                    callback.failed()
                }
            }
        })
    }
}