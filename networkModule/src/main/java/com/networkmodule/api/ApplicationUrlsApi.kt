package com.networkmodule.api


import com.networkmodule.model.GetAppAssetsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApplicationUrlsApi {
    @Headers(
        "Content-Type:application/json"
    )
    @GET("{package_id}/all-assets/")
    fun getAppAssetsByPackageId(
        @retrofit2.http.Header("X-Access-Token") accessToken: String? = null,
        @retrofit2.http.Path("package_id") packageId: String? = null
    ): Call<GetAppAssetsResponse>
}