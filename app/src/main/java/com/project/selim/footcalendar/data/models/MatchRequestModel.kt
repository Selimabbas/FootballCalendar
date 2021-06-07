package com.project.selim.footcalendar.data.models

import com.google.gson.annotations.SerializedName

data class Matches(@SerializedName("matches") val matches: List<Match>)

data class MatchTeam(@SerializedName("id") val id: Int, @SerializedName("name") val name: String)

data class Match(
    @SerializedName("id") val id: Int,
    @SerializedName("homeTeam") val homeTeam: MatchTeam,
    @SerializedName("awayTeam") val awayTeam: MatchTeam
)
