package com.project.selim.footcalendar.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun createNetwork(): FootApi = Retrofit.Builder()
    .baseUrl("https://api.football-data.org/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(makeHttpClient())
    .build()
    .create(FootApi::class.java) // todo remove line when using di

private fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(
        chain.request().newBuilder()
            .addHeader("X-Auth-Token", "45a1057fa7154624a9e2ec306146ea77")
            .build()
    )
}

private fun logInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun makeHttpClient() = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)
    .addInterceptor(headersInterceptor())
    .addInterceptor(logInterceptor())
    .build()
