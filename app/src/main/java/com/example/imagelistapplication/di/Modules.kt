package com.example.imagelistapplication.di

import com.example.imagelistapplication.model.save.ImageSaveExternal
import com.example.imagelistapplication.model.scrape.GettyImageScraper
import com.example.imagelistapplication.model.repository.ImageRepository
import com.example.imagelistapplication.model.repository.ImageRepositoryImpl
import com.example.imagelistapplication.ui.detail.ImageDetailContract
import com.example.imagelistapplication.ui.detail.ImageDetailFragment
import com.example.imagelistapplication.ui.detail.ImageDetailPresenter
import com.example.imagelistapplication.ui.images.ImagesContract
import com.example.imagelistapplication.ui.images.ImagesFragment
import com.example.imagelistapplication.ui.images.ImagesPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val presenterModule = module {
    factory<ImagesContract.Presenter> { (view: ImagesFragment) -> ImagesPresenter(view, get()) }
    factory<ImageDetailContract.Presenter> { (view: ImageDetailFragment) -> ImageDetailPresenter(view, get()) }
}

val repositoryModule = module {
    factory<ImageRepository> { ImageRepositoryImpl(ImageSaveExternal(androidContext()), GettyImageScraper()) }
}