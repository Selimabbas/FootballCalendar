package com.project.selim.footcalendar

/**
 * Match
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 17/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
object MatchRequestModel {
    data class Matches(val matches:List<Match>)
    data class Team(val id: Int, val name: String)
    data class Match(val id: Int, val homeTeam: Team, val awayTeam: Team)
}
