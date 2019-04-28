package com.project.selim.footcalendar

/**
 * CompetitionsRequestModel
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 20/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
class CompetitionsRequestModel {
    data class Competitions(val competitions : List<Competition>)
    data class Competition(val name : String, val id : Int, val area : Area)
    data class Area(val name: String)
}