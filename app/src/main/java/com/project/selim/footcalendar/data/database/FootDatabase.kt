package com.project.selim.footcalendar.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.project.selim.footcalendar.CompetitionsRequestModel

/**
 * FootDatabase
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 22/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */

@Database(entities = [CompetitionsRequestModel.Competition::class], version = 1)
abstract class FootDatabase : RoomDatabase() {

    abstract fun footDataDao(): FootDataDao

    companion object {
        private var INSTANCE: FootDatabase? = null

        fun getInstance(context: Context): FootDatabase? {
            if (INSTANCE == null) {
                synchronized(FootDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            FootDatabase::class.java, "foot.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}