package com.project.selim.footcalendar.di

import com.project.selim.footcalendar.data.FootRepository
import org.koin.dsl.module

val repositoryModule = module {
    single(createdAtStart = true) { FootRepository() }
}