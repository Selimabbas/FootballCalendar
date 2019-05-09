package com.project.selim.footcalendar.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * CompetitionData
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 22/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */

@Entity(tableName = "competitionTable")
data class CompetitionData (@PrimaryKey(autoGenerate = true) var id:Long?,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "competition_id") var competitionId: Int
)