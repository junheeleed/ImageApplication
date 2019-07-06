package com.example.imagelistapplication.util

import android.os.Environment

fun isMediaMounted(): Boolean = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)