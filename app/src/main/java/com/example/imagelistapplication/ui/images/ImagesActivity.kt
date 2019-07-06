package com.example.imagelistapplication.ui.images

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.imagelistapplication.R
import com.example.imagelistapplication.util.addFragment

class ImagesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        addFragment(supportFragmentManager, R.id.framelayout, ImagesFragment())
    }
}