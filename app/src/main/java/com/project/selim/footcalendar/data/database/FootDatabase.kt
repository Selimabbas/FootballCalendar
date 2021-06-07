package com.project.selim.footcalendar.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.project.selim.footcalendar.data.models.Competition

/**
 * FootDatabase
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 22/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */

@Database(entities = [Competition::class], version = 1)
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