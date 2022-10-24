package com.example.baisecomposelearn.wanandroid

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://www.wanandroid.com"
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build()
    fun <T> create(serviceClass: Class<T>):T = retrofit.create(serviceClass)
    inline fun <reified T> create():T = create(T::class.java)
}
