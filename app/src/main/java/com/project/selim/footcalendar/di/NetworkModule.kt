package com.project.selim.footcalendar.di

import com.project.selim.footcalendar.data.network.createNetwork
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { createNetwork() }
}

