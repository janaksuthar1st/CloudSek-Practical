package com.cloudsek.practicaljanak.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.cloudsek.practicaljanak.R
import com.cloudsek.practicaljanak.common.Resource
import com.cloudsek.practicaljanak.repository.network.CloudSekNetworkRepository
import com.cloudsek.practicaljanak.utils.CommonUtils.checkInternetConnected

class HomeScreenViewModel(
    application: Application,
    private val cloudSekNetworkRepository: CloudSekNetworkRepository
) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val mContext = application.applicationContext

    val fetchAppAssetsResponse: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    fun fetchAppAssetsByPackage(packageName: String) {
        if (!mContext.checkInternetConnected()) {
            fetchAppAssetsResponse.value =
                Resource.NoInternetError<String>(mContext.getString(R.string.default_internet_message))
        } else {
            fetchAppAssetsResponse.addSource(
                cloudSekNetworkRepository.callGetAppsAssetsByPackage(packageName)
            ) {
                fetchAppAssetsResponse.value = it
            }
        }
    }
}