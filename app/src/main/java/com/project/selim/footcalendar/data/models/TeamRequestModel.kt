package com.project.selim.footcalendar.data.models

data class Teams(val teams: List<Team>)

data class Team(val id: Int, val name: String, val shortName: String)