package com.aengussong.instacopy.di

import com.aengussong.instacopy.DataViewModel
import com.aengussong.instacopy.repo.InstaRepository
import com.aengussong.instacopy.repo.Repository
import com.aengussong.instacopy.repo.local.LocalDataProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    single { LocalDataProvider(get()) }

    single<Repository> { InstaRepository(get()) }

    viewModel { DataViewModel(get()) }
}