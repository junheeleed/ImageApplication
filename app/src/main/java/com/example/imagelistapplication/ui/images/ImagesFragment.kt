package com.example.imagelistapplication.ui.images

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.imagelistapplication.databinding.FragmentImagesBinding
import com.example.imagelistapplication.model.scrape.data.Image
import com.example.imagelistapplication.ui.IntentKeys
import com.example.imagelistapplication.ui.detail.ImageDetailActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ImagesFragment: Fragment(), ImagesContract.View {

    private lateinit var dataBinding: FragmentImagesBinding
    private val presenter: ImagesContract.Presenter by inject { parametersOf(this@ImagesFragment)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentImagesBinding.inflate(inflater, container, false)
        presenter.getImages()
        return dataBinding.root
    }

    override fun setProgressBarVisibility(visibility: Int) {
        dataBinding.progressbar.visibility = visibility
    }

    override fun showToast(resource: Int) {
        Toast.makeText(context, resources.getString(resource), Toast.LENGTH_SHORT).show()
    }

    override fun setRecyclerView(images: List<Image>) {
        with(dataBinding.recyclerview) {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = ImageAdapter(images, object : ImageAdapter.OnItemClickListener {
                override fun onItemClick(image: Image) {
                    startActivity(Intent(activity, ImageDetailActivity::class.java).apply {
                        putExtra(IntentKeys.SELECTED_ITEM_INFO, image)
                    })
                }
            })
        }
    }

    override fun closeScreen() { activity!!.finish() }
}