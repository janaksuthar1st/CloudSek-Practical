package com.cloudsek.practicaljanak.di

import com.cloudsek.practicaljanak.viewmodels.HomeScreenViewModel
import com.cloudsek.practicaljanak.viewmodels.SplashViewModel
import org.koin.dsl.module

import org.koin.androidx.viewmodel.dsl.viewModel

val uiModules = arrayOf(
    module {
        viewModel { SplashViewModel(get()) }
        viewModel { HomeScreenViewModel(get(),get()) }
    }
)