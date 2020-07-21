package com.aengussong.instacopy.repo

import com.aengussong.instacopy.model.Post
import com.aengussong.instacopy.repo.local.LocalDataProvider

class InstaRepository(private val localProvider: LocalDataProvider) : Repository {

    override fun loadData(): Array<Post> = localProvider.loadData()

}