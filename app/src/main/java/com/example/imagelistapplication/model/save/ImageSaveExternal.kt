package com.example.imagelistapplication.model.save

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import com.example.imagelistapplication.model.scan.MediaScanner
import java.io.*

class ImageSaveExternal(private val context: Context): ImageSavable {

    private lateinit var callback: ImageSavable.Callback

    override fun save(bitmap: Bitmap, imageName: String, callback : ImageSavable.Callback) {
        this.callback = callback
        if (isExist(getPath(imageName))) {
            callback.existed()
            return
        }
        try {
            val path = getPath(imageName)
            writeImage(bitmap, path)
            scanFile(path)
        } catch (e: IOException) {
            e.printStackTrace()
            callback.failed()
        }
    }

    private fun writeImage(bitmap: Bitmap, imagePath: String) {
        val byteArrayOutputStream = getByteArrayOutputStream(bitmap)
        write(ByteArrayInputStream(byteArrayOutputStream.toByteArray()), File(imagePath).outputStream(), byteArrayOutputStream.size())
    }

    private fun getByteArrayOutputStream(bitmap: Bitmap): ByteArrayOutputStream
            = ByteArrayOutputStream().apply {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, this) }

    private fun write(byteArrayInputStream: ByteArrayInputStream, fileOutputStream: FileOutputStream, byteArraySize: Int) {
        val buffer = ByteArray(1024)
        var copiedSize = 0

        while ((byteArrayInputStream.read(buffer)).also {
                    copiedSize += it } > 0) {
            callback.saving(getPercentage(copiedSize, byteArraySize))
            fileOutputStream.write(buffer, 0, buffer.size)
        }
        fileOutputStream.close()
    }

    private fun getPercentage(copiedSize: Int, totalSize: Int): Int = (copiedSize * 100) / totalSize;

    private fun scanFile(filePath: String) {
        MediaScanner.apply {
            init(context, object : MediaScanner.Callback {
                override fun scanCompleted() {
                    callback.saved()
                }
            })
            scanFile(filePath)
        }
    }

    private fun isExist(imagePath: String): Boolean = File(imagePath).exists()

    private fun getPath(name: String): String {
        val extension = ".jpeg"
        return "${Environment.getExternalStorageDirectory()}" + File.separator + name + extension
    }
}