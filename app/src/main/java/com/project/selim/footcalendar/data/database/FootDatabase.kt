package com.project.selim.footcalendar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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

        fun getDatabase(context: Context): FootDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FootDatabase::class.java, "foot.db"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}