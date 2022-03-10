package com.cloudsek.practicaljanak.repository.network

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.cloudsek.practicaljanak.BuildConfig
import com.cloudsek.practicaljanak.R
import com.cloudsek.practicaljanak.common.Resource
import com.networkmodule.ApiClient
import com.networkmodule.api.ApplicationUrlsApi
import com.networkmodule.model.GetAppAssetsResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CloudSekNetworkRepository(val mContext: Context) {

    private val applicationURLAPIClient: ApplicationUrlsApi by lazy {
        ApiClient.createService(ApplicationUrlsApi::class.java)
    }

    fun callGetAppsAssetsByPackage(packageName: String): MutableLiveData<Any> {
        val data = MutableLiveData<Any>()
        val appURLCall = applicationURLAPIClient.getAppAssetsByPackageId(
            accessToken = BuildConfig.ACCESS_TOKEN,
            packageId = packageName
        )

        data.value = Resource.Loading<Boolean>(true)

        appURLCall.enqueue(object : Callback<GetAppAssetsResponse> {
            override fun onFailure(call: Call<GetAppAssetsResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Resource.Loading<Boolean>(false)
                data.value =
                    Resource.Error<String>(mContext.getString(R.string.lbl_something_went_wrong))
            }

            override fun onResponse(
                call: Call<GetAppAssetsResponse>,
                response: Response<GetAppAssetsResponse>
            ) {

                data.value = Resource.Loading<Boolean>(false)
                if (response.isSuccessful) {
                    val mBean: GetAppAssetsResponse? = response.body()
                    data.value = Resource.Success(mBean)
                }else if (response.code() == 400){
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    data.value =
                        Resource.Error<String>(jObjError.getString("detail"))
                } else {
                    data.value =
                        Resource.Error<String>(mContext.getString(R.string.lbl_something_went_wrong))
                }
            }
        })

        return data
    }
}