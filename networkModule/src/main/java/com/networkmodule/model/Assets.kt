package com.networkmodule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Assets(
    @SerializedName("Amazon Execute-API URL")
    var amazonExecuteAPIURL: ArrayList<String> = arrayListOf(),
    @SerializedName("email")
    var email: ArrayList<String> = arrayListOf(),
    @SerializedName("filename")
    var filename: ArrayList<String> = arrayListOf(),
    @SerializedName("file_path")
    var filePath: ArrayList<String> = arrayListOf(),
    @SerializedName("host")
    var host: ArrayList<String> = arrayListOf(),
    @SerializedName("IP Address disclosure")
    var iPAddressDisclosure: ArrayList<String> = arrayListOf(),
    @SerializedName("rest_api")
    var restApi: ArrayList<String> = arrayListOf(),
    @SerializedName("url")
    var url: ArrayList<String> = arrayListOf()
) : Parcelable