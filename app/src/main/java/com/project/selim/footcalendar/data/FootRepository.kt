package com.project.selim.footcalendar.data

import com.project.selim.footcalendar.data.models.Competitions
import com.project.selim.footcalendar.data.models.Matches
import com.project.selim.footcalendar.data.network.ApiPlan
import com.project.selim.footcalendar.data.network.ApiResult

/**
 * FootRepository
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 23/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */

class FootRepository : BaseRepository() {
    suspend fun getMatches(): ApiResult<Matches> {
        return apiCall(footApi.getMatches())
    }

    suspend fun getCompetitions(apiPlan: ApiPlan): ApiResult<Competitions> {
        return apiCall(footApi.getCompetitions(apiPlan.name))
    }

    suspend fun getTeams(apiPlan: ApiPlan): ApiResult<Competitions> {
        return apiCall(footApi.getTeams(apiPlan.name))
    }
}