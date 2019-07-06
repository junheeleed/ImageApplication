package com.example.imagelistapplication.model.scrape

class GettyField {

    companion object {
        val URL: String = "https://www.gettyimages.com/photos/collaboration?sort=mostpopular&mediatype=photography&phrase=collaboration&license=rf,rm&page=1&recency=anydate&suppressfamilycorrection=true"
        val CLASS_NAME: String = ".search-content__gallery-assets"
        val TAG: String = "img[class=gallery-asset__thumb gallery-mosaic-asset__thumb]"
        val ATTR: String = "src"
        val ATTR1: String = "class"
        val ATTR2: String = "alt"
    }
}