package com.project.selim.footcalendar.data.network

import com.project.selim.footcalendar.data.models.Competitions
import com.project.selim.footcalendar.data.models.Matches
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FootApi {

    @GET("competitions/CL/matches")
    suspend fun getMatches(): Response<Matches>

    @GET("competitions")
    suspend fun getCompetitions(@Query("plan") plan: String): Response<Competitions>

    @GET("CL/teams")
    suspend fun getTeams(@Query("plan") plan: String): Response<Competitions>
}