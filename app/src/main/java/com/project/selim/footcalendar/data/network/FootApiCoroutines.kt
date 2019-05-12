package com.project.selim.footcalendar.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.project.selim.footcalendar.data.models.CompetitionsRequestModel
import com.project.selim.footcalendar.data.models.MatchRequestModel
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface FootApiCoroutines {

    @GET("competitions/CL/matches")
    fun getMatches():
            Deferred<MatchRequestModel.Matches>

    @GET("competitions")
    fun getCompetitions(@Query("plan") plan: String):
            Deferred<CompetitionsRequestModel.Competitions>

    @GET("CL/teams")
    fun getTeams(@Query("plan") plan: String):
            Deferred<CompetitionsRequestModel.Competitions>

    companion object {
        fun create(): FootApiCoroutines {

            val retrofit = Retrofit.Builder()
                    .client(makeHttpClient())
                    .baseUrl("https://api.football-data.org/v2/")
                    .addConverterFactory(
                            MoshiConverterFactory.create())
                    .addCallAdapterFactory(
                            CoroutineCallAdapterFactory())
                    .build()

            return retrofit.create(FootApiCoroutines::class.java)
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