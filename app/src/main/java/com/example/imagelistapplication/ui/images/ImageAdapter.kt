package com.example.imagelistapplication.ui.images

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.imagelistapplication.databinding.RecyclerViewItemBinding
import com.example.imagelistapplication.model.scrape.data.Image

class ImageAdapter(private val images: List<Image>,
                   private val listener: OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(image: Image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(RecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.context),
                        parent, false), listener)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as ViewHolder).init(images.get(position))
    }

    override fun getItemCount(): Int = images.size
}