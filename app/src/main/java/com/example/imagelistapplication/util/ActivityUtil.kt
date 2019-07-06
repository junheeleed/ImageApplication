package com.example.imagelistapplication.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

fun addFragment(fragmentManager: FragmentManager, layout: Int, fragment: Fragment) {
    fragmentManager.beginTransaction().apply {
        add(layout, fragment)
        commit()
    }
}