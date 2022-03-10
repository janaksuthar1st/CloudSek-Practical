package com.cloudsek.practicaljanak.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    var splashNavigate: MutableLiveData<Boolean> = MutableLiveData()

    fun startTimer() {
        try {
            launch(Dispatchers.Main){
                delay(1500)
                splashNavigate.postValue(true)
            }
        }catch (e:Exception){
            splashNavigate.postValue(false)
        }
    }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}
