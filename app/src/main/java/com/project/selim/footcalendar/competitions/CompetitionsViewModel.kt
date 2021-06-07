package com.project.selim.footcalendar.competitions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.selim.footcalendar.Event
import com.project.selim.footcalendar.data.FootRepository
import com.project.selim.footcalendar.data.models.Competitions
import com.project.selim.footcalendar.data.network.ApiPlan
import com.project.selim.footcalendar.data.network.ApiResult
import kotlinx.coroutines.*

class CompetitionsViewModel : ViewModel() {
    var competitions = MutableLiveData<Competitions>()

    val errorEvent = MutableLiveData<Event<String>>()

    private val repository = FootRepository()

    private var myJob: Job? = null

    fun loadCompetitions() {
        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getCompetitions(ApiPlan.TIER_ONE)
            withContext(Dispatchers.Main) {
                when (result) {
                    is ApiResult.Error -> setError(result.exception)
                    is ApiResult.Success -> setCompetitions(result.data)
                }
            }
        }
    }

    private fun setCompetitions(competitions: Competitions) {
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