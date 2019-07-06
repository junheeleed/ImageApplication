package com.example.imagelistapplication.ui.images

import android.view.View
import com.example.imagelistapplication.R
import com.example.imagelistapplication.model.scrape.ImageScrapable
import com.example.imagelistapplication.model.scrape.data.Image
import com.example.imagelistapplication.model.repository.ImageRepository
import com.example.imagelistapplication.util.MainThreadExecutor

class ImagesPresenter(private val view: ImagesContract.View,
                      private val imageRepository: ImageRepository) : ImagesContract.Presenter {

    override fun getImages() {
        imageRepository.getImages(object : ImageScrapable.Callback {
            override fun succeed(images: List<Image>) {
                MainThreadExecutor.execute(object : Runnable {
                    override fun run() {
                        view.setProgressBarVisibility(View.INVISIBLE)
                        view.setRecyclerView(images)
                        view.showToast(R.string.main_images_load_succcessd)
                    }
                })
            }

            override fun failed() {
                MainThreadExecutor.execute(object : Runnable {
                    override fun run() {
                        view.setProgressBarVisibility(View.INVISIBLE)
                        view.showToast(R.string.main_images_load_failed)
                        view.closeScreen()
                    }
                })
            }
        })
    }
}