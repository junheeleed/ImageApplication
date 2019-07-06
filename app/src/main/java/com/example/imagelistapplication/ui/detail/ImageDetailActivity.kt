package com.example.imagelistapplication.ui.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.imagelistapplication.R
import com.example.imagelistapplication.ui.IntentKeys
import com.example.imagelistapplication.util.addFragment

class ImageDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)
        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar)

        addFragment(supportFragmentManager, R.id.framelayout, ImageDetailFragment().apply {
            arguments = getBundle()
        })
    }

    private fun getBundle(): Bundle = Bundle().apply {
        putSerializable(IntentKeys.SELECTED_ITEM_INFO
                , intent.getSerializableExtra(IntentKeys.SELECTED_ITEM_INFO))
    }
}