package com.aengussong.instacopy.repo.local

import android.content.Context
import com.aengussong.instacopy.R
import com.aengussong.instacopy.model.Post
import com.google.gson.Gson

class LocalDataProvider(private val context: Context) {

    fun loadData(): Array<Post> {
        val postsJson: String = context.resources.openRawResource(R.raw.instadata).bufferedReader()
            .use { it.readText() }
        return Gson().fromJson(postsJson, Array<Post>::class.java)
    }
}