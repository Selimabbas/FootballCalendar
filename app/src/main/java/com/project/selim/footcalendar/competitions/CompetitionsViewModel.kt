package com.project.selim.footcalendar.competitions

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.project.selim.footcalendar.data.network.ApiPlan
import com.project.selim.footcalendar.CompetitionsRequestModel
import com.project.selim.footcalendar.Event
import com.project.selim.footcalendar.data.network.FootApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * CompetitionsViewModel
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 20/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
class CompetitionsViewModel : ViewModel() {
    var competitions = MutableLiveData<CompetitionsRequestModel.Competitions>()

    val errorEvent = MutableLiveData<Event<String>>()

    private val footApiServe by lazy {
        FootApi.create()
    }
    private var disposable: Disposable? = null

    fun updateCompetitions() {
        disposable = footApiServe.getCompetitions(ApiPlan.TIER_ONE.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> setCompetitions(result) },
                        { error -> setError(error) }
                )

    }

    private fun setCompetitions(competitions: CompetitionsRequestModel.Competitions) {
        this.competitions.value = competitions
    }

    private fun setError(error: Throwable) {
        error.message?.let { message ->
            errorEvent.value = Event(message)
        }
    }

}