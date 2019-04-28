package com.project.selim.footcalendar.matchescalendar

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.project.selim.footcalendar.Event
import com.project.selim.footcalendar.data.network.FootApi
import com.project.selim.footcalendar.MatchRequestModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * ViewModel
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 17/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
class CalendarViewModel : ViewModel() {

    var matches = MutableLiveData<MatchRequestModel.Matches>()

    val errorEvent = MutableLiveData<Event<String>>()

    private val footApiServe by lazy {
        FootApi.create()
    }
    private var disposable: Disposable? = null

    fun callNetwork() {
        disposable = footApiServe.getMatches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> setMatches(result) },
                        { error -> setError(error) }
                )

    }

    private fun setMatches(matches: MatchRequestModel.Matches) {
        this.matches.value = matches
    }

    private fun setError(error: Throwable) {
        error.message?.let { message ->
            errorEvent.value = Event(message)
        }
    }
}