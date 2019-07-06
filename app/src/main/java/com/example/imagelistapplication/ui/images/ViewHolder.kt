package com.example.imagelistapplication.ui.images

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.imagelistapplication.databinding.RecyclerViewItemBinding
import com.example.imagelistapplication.model.scrape.data.Image
import com.example.imagelistapplication.util.fromUrl

class ViewHolder(private val databinding: RecyclerViewItemBinding,
                 private val listener: ImageAdapter.OnItemClickListener): RecyclerView.ViewHolder(databinding.root) {

    fun init(image: Image) {
        databinding.image.fromUrl(image.url, 1000, 400)
        itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener.onItemClick(image)
            }
        })
    }
}