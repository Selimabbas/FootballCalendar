package com.project.selim.footcalendar.data.network

import com.project.selim.footcalendar.data.models.CompetitionsRequestModel
import com.project.selim.footcalendar.data.models.MatchRequestModel
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


/**
 * FootApi
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 17/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
interface FootApi {

    @GET("competitions/CL/matches")
    fun getMatches():
            Observable<MatchRequestModel.Matches>

    @GET("competitions")
    fun getCompetitions(@Query("plan") plan: String):
            Observable<CompetitionsRequestModel.Competitions>

    @GET("CL/teams")
    fun getTeams(@Query("plan") plan: String):
            Observable<CompetitionsRequestModel.Competitions>


    companion object {
        fun create(): FootApi {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://api.football-data.org/v2/")
                    .client(makeHttpClient())
                    .build()

            return retrofit.create(FootApi::class.java)
        }

        private fun headersInterceptor() = Interceptor { chain ->
            chain.proceed(chain.request().newBuilder()
                    .addHeader("X-Auth-Token", "45a1057fa7154624a9e2ec306146ea77")
                    .build())
        }

        private fun makeHttpClient() = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(headersInterceptor())
                .build()
    }
}