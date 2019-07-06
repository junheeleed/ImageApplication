package com.example.imagelistapplication.ui.detail

import android.graphics.Bitmap
import com.example.imagelistapplication.R
import com.example.imagelistapplication.model.save.ImageSavable
import com.example.imagelistapplication.model.repository.ImageRepository
import com.example.imagelistapplication.util.MainThreadExecutor

class ImageDetailPresenter(private val view: ImageDetailContract.View,
                           private val imageRepository: ImageRepository): ImageDetailContract.Presenter {

    override fun saveImage(bitmap: Bitmap, imageName: String) {
        imageRepository.save(bitmap, imageName, object : ImageSavable.Callback {
            override fun saved() {
                MainThreadExecutor.execute(object : Runnable {
                    override fun run() {
                        view.showToast(R.string.image_saved_message)
                    }
                })
            }

            override fun saving(percentage: Int) {
                MainThreadExecutor.execute(object : Runnable {
                    override fun run() {
                        view.setProgress(percentage)
                    }
                })
            }

            override fun failed() {
                MainThreadExecutor.execute(object : Runnable {
                    override fun run() {
                        view.showToast(R.string.image_failed_message)
                    }
                })
            }

            override fun existed() {
                MainThreadExecutor.execute(object : Runnable {
                    override fun run() {
                        view.showExistenceDialog()
                    }
                })
            }
        })
    }
}