package com.project.selim.footcalendar.di

import com.project.selim.footcalendar.competitions.CompetitionsViewModel
import com.project.selim.footcalendar.matchescalendar.CalendarViewModel
import com.project.selim.footcalendar.teams.TeamsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { CalendarViewModel(get()) }
    viewModel { CompetitionsViewModel(get()) }
    viewModel { TeamsViewModel(get()) }
}