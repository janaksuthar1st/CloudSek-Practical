package com.cloudsek.practicaljanak.utils

import com.bumptech.glide.load.model.GlideUrl

class GlideUrlWithCache(private val mSourceUrl: String) : GlideUrl(mSourceUrl) {

    override fun getCacheKey(): String {
        return mSourceUrl.split("?")[0]
    }

    override fun toString(): String {
        return super.getCacheKey()
    }
}