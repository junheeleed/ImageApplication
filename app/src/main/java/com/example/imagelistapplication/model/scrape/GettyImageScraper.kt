package com.example.imagelistapplication.model.scrape

import android.text.TextUtils
import com.example.imagelistapplication.model.scrape.data.Image
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

class GettyImageScraper: ImageScrapable {

    override fun getImages(): List<Image> = getImages(getElements())

    private fun getElements(): Elements? {
        try {
            return Jsoup.connect(GettyField.URL)
                    .userAgent("Mozilla")
                    .get()
                    .select(GettyField.CLASS_NAME)
                    .select(GettyField.TAG)
        } catch (e: IOException) {
            return null
        }
    }

    private fun getImages(elements: Elements?): List<Image> {
        when (elements) {
            null -> return listOf()
            else -> return getAddedImages(elements)
        }
    }

    private fun getAddedImages(elements: Elements): List<Image> {
        var images = mutableListOf<Image>()

        for (element in elements) {
            val url = element.attr(GettyField.ATTR)
            if (!TextUtils.isEmpty(url)) {
                images.add(Image(url,
                        className = element.attr(GettyField.ATTR1),
                        content = element.attr(GettyField.ATTR2)))
            }
        }
        return images
    }
}