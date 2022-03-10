package com.cloudsek.practicaljanak.di

import com.cloudsek.practicaljanak.repository.network.CloudSekNetworkRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { CloudSekNetworkRepository(get()) }
}