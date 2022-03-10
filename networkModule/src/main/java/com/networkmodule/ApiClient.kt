package com.networkmodule

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private fun retrofitBuilder(): Retrofit {
        val baseUrl = BuildConfig.BASE_URL

        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofitBuilder().create(serviceClass)
    }

    private fun getOkHttpClient(): OkHttpClient {

        val builder = OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }

        return builder.build()
    }
}