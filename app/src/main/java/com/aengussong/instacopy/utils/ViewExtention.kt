package com.aengussong.instacopy.utils

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

inline fun <T, reified R : View> R.doAsync(
    crossinline backgroundTask: suspend () -> T?
) {
    val job = CoroutineScope(Dispatchers.Main)
    val attachListener = object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(p0: View?) {}
        override fun onViewDetachedFromWindow(p0: View?) {
            job.cancel()
        }
    }
    addOnAttachStateChangeListener(attachListener)
    job.launch {
        try {
            backgroundTask()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        removeOnAttachStateChangeListener(attachListener)
    }
}