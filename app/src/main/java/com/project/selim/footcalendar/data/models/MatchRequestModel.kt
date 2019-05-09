package com.project.selim.footcalendar.data.models

object MatchRequestModel {
    data class Matches(val matches:List<Match>)
    data class Team(val id: Int, val name: String)
    data class Match(val id: Int, val homeTeam: Team, val awayTeam: Team)
}
