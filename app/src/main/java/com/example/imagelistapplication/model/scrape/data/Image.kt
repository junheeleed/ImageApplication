package com.example.imagelistapplication.model.scrape.data

import java.io.Serializable

class Image(val url: String,
            val className: String,
            val content: String) : Serializable {
}