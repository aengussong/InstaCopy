package com.aengussong.instacopy

import androidx.lifecycle.ViewModel
import com.aengussong.instacopy.repo.Repository

class DataViewModel(private val repo: Repository) : ViewModel() {

    fun loadData() = repo.loadData()

}