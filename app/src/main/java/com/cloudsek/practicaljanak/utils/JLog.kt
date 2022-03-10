package com.cloudsek.practicaljanak.utils

import android.util.Log
import com.cloudsek.practicaljanak.BuildConfig
import java.lang.Exception

object JLog {

    private val LOG = BuildConfig.DEBUG
    fun i(tag: String, string: String) {
        if (LOG) {
            Log.i(tag, string)
        }
    }

    fun e(tag: String, string: String) {
        if (LOG) {
            Log.e(tag, string)
        }
    }

    fun d(tag: String, string: String) {
        if (LOG) {
            Log.d(tag, string)
        }
    }

    fun v(tag: String, string: String) {
        if (LOG) {
            Log.v(tag, string)
        }
    }

    fun w(tag: String, string: String) {
        if (LOG) {
            Log.w(tag, string)
        }
    }

    fun isLog(): Boolean {
        return LOG
    }

}