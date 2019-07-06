package com.example.imagelistapplication.model.scan

import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri

object MediaScanner {

    private lateinit var callback: Callback
    private var mediaScannerConnection: MediaScannerConnection ?= null
    private lateinit var filePath: String

    interface Callback {
        fun scanCompleted()
    }

    fun init(context: Context, callback: Callback) {
        if (mediaScannerConnection == null) {
            mediaScannerConnection = MediaScannerConnection(context, mediaScannerConnectionClient)
        }
        this.callback = callback
    }

    fun scanFile(filePath: String) {
        this.filePath = filePath
        mediaScannerConnection!!.connect()
    }

    private val mediaScannerConnectionClient: MediaScannerConnection.MediaScannerConnectionClient
            = object : MediaScannerConnection.MediaScannerConnectionClient {

        override fun onMediaScannerConnected() {
            mediaScannerConnection!!.scanFile(filePath, null)
        }

        override fun onScanCompleted(path: String?, uri: Uri?) {
            mediaScannerConnection!!.disconnect()
            callback.scanCompleted()
        }
    }
}