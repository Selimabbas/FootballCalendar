package com.project.selim.footcalendar.matchescalendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.selim.footcalendar.Event
import com.project.selim.footcalendar.data.network.FootApi
import com.project.selim.footcalendar.data.models.MatchRequestModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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