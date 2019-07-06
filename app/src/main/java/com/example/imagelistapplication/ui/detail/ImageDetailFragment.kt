package com.example.imagelistapplication.ui.detail

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.imagelistapplication.R
import com.example.imagelistapplication.databinding.FragmentImageDetailBinding
import com.example.imagelistapplication.model.scrape.data.Image
import com.example.imagelistapplication.ui.IntentKeys
import com.example.imagelistapplication.util.ImageSetCallBack
import com.example.imagelistapplication.util.fromUrl
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ImageDetailFragment: Fragment(), ImageDetailContract.View {

    private lateinit var dataBinding: FragmentImageDetailBinding
    private val presenter: ImageDetailContract.Presenter by inject { parametersOf(this@ImageDetailFragment) }
    lateinit var image: Image

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentImageDetailBinding.inflate(inflater, container, false).apply {
            fragment = this@ImageDetailFragment
        }
        image = arguments!!.getSerializable(IntentKeys.SELECTED_ITEM_INFO) as Image
        setUi()
        return dataBinding.root
    }

    fun setUi() {
        dataBinding.image.fromUrl(image.url, object : ImageSetCallBack {
            override fun callback(isSet: Boolean) {
                if(isSet) {
                    dataBinding.imageProgressbar.visibility = View.INVISIBLE
                    dataBinding.imageSaveFab.show()
                }
            }
        })
    }

    fun onImageSaveFabClick() {
        presenter.saveImage((dataBinding.image.drawable as BitmapDrawable).bitmap
                , dataBinding.imageContent.text.toString())
    }

    override fun setProgress(percentage: Int) {
        dataBinding.savingImageProgressbar.setProgress(percentage)
        dataBinding.savingImageTextview.setText("$percentage" + resources.getString(R.string.percentage_symbol))
    }

    override fun showToast(resource: Int) {
        Toast.makeText(context, resources.getString(resource), Toast.LENGTH_SHORT).show()
    }

    override fun showExistenceDialog() {
        AlertDialog.Builder(context!!).apply {
            setMessage(R.string.show_overwrite_image_dialog_message)
        }.create().show()
    }
}