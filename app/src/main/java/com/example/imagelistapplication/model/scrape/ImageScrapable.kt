package com.example.imagelistapplication.model.scrape

import com.example.imagelistapplication.model.scrape.data.Image

interface ImageScrapable {
    fun getImages(): List<Image>

    interface Callback {
        fun succeed(images: List<Image>)
        fun failed()
    }
}