package com.project.selim.footcalendar

import android.app.Application
import com.project.selim.footcalendar.data.database.FootDatabase
import com.project.selim.footcalendar.di.remoteDataSourceModule
import com.project.selim.footcalendar.di.repositoryModule
import com.project.selim.footcalendar.di.uiModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val appModule = listOf(uiModule, repositoryModule, remoteDataSourceModule)