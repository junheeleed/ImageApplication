package com.example.imagelistapplication.ui.detail

import android.graphics.Bitmap

interface ImageDetailContract {

    interface View {
        fun setProgress(percentage: Int)
        fun showToast(resource: Int)
        fun showExistenceDialog()
    }

    interface Presenter {
        fun saveImage(bitmap: Bitmap, imageName: String)
    }
}