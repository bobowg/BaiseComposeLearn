package com.example.baisecomposelearn.screens.components.net

import com.example.baisecomposelearn.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// https://baobab.kaiyanapp.com/api/v4/discovery/hot?start=1&num=2
const val BASE_URL = "http://baobab.kaiyanapp.com"


object RetrofitClient {
    private val instance: Retrofit by lazy {
        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(logInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
        Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun <T> createApi(clazz: Class<T>):T{
        return instance.create(clazz) as T
    }
}