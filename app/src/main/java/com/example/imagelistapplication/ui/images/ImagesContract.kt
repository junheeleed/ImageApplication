package com.example.imagelistapplication.ui.images

import com.example.imagelistapplication.model.scrape.data.Image

interface ImagesContract {

    interface View {
        fun setProgressBarVisibility(visibility: Int)
        fun showToast(resource: Int)
        fun setRecyclerView(images: List<Image>)
        fun closeScreen()
    }

    interface Presenter {
        fun getImages()
    }
}