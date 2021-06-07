package com.project.selim.footcalendar.data.models

data class Competitions(val competitions: List<Competition>)

data class Competition(val name: String, val id: Int, val area: Area)

data class Area(val name: String)
