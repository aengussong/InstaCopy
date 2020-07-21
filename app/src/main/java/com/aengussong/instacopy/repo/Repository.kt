package com.aengussong.instacopy.repo

import com.aengussong.instacopy.model.Post

interface Repository {

    fun loadData(): Array<Post>
}