package com.project.selim.footcalendar.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * FootDataDao
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 22/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */

@Dao
interface FootDataDao {
    @Query("SELECT * from competitionTable where competition_id in :competitions")
    fun getCompetitions(competitions: List<Int>): List<CompetitionData>

    @Insert
    fun addCompetition(competition: CompetitionData)

    @Query("DELETE from competitionTable where competition_id = competition_id")
    fun removeCompetition(competitionId: Int)

    @Query("DELETE from competitionTable")
    fun deleteAll()

}