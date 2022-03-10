package com.cloudsek.practicaljanak.base

import androidx.multidex.MultiDexApplication
import com.cloudsek.practicaljanak.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyBaseApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        //Initializing koin
        startKoin {
            androidContext(this@MyBaseApp)
            modules(allModules)
        }
    }
}