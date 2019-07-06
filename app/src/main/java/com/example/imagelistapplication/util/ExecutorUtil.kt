package com.example.imagelistapplication.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object MainThreadExecutor: Executor {
    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun execute(runnable: Runnable) {
        handler.post(runnable)
    }
}

object ThreadExecutor {
    fun executeToSingleThread(runnable: Runnable) {
        Executors.newSingleThreadExecutor().apply {
            execute(runnable)
            shutdown()
        }
    }
}