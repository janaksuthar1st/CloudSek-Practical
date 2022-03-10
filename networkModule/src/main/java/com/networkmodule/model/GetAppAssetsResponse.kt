package com.networkmodule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetAppAssetsResponse(
    @SerializedName("package_id") var packageId: String? = null,
    @SerializedName("assets") var assets: Assets? = null,
    @SerializedName("detail") var detail: String? = null
) : Parcelable