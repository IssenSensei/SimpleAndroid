package com.issen.simpleandroid.data.remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = " https://jsonplaceholder.typicode.com/"

var gson = GsonBuilder().setLenient().create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object SimpleAndroidApi {
    val simpleAndroidService : SimpleAndroidService by lazy { retrofit.create(SimpleAndroidService::class.java) }
}

