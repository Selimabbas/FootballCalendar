package com.project.selim.footcalendar.competitions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.selim.footcalendar.Event
import com.project.selim.footcalendar.data.models.CompetitionsRequestModel
import com.project.selim.footcalendar.data.network.ApiPlan
import com.project.selim.footcalendar.data.network.FootApiCoroutines
import kotlinx.coroutines.*

class CompetitionsViewModel : ViewModel() {
    var competitions = MutableLiveData<CompetitionsRequestModel.Competitions>()

    val errorEvent = MutableLiveData<Event<String>>()

    private val footApiServe by lazy {
        FootApiCoroutines.create()
    }

    private var myJob: Job? = null

    fun loadCompetitions() {
        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = footApiServe.getCompetitions(ApiPlan.TIER_ONE.name).await()
            withContext(Dispatchers.Main) {
                setCompetitions(result)
            }
        }
        /*
        footApiServe.getCompetitions(ApiPlan.TIER_ONE.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> setCompetitions(result) },
                        { error -> setError(error) }
                )
*/
    }

    private fun setCompetitions(competitions: CompetitionsRequestModel.Competitions) {
        this.competitions.value = competitions
    }

    private fun setError(error: Throwable) {
        error.message?.let { message ->
            errorEvent.value = Event(message)
        }
    }

    override fun onCleared() {
        myJob?.cancel()
        super.onCleared()
    }
}