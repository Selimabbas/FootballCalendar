package com.project.selim.footcalendar.matchescalendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.selim.footcalendar.Event
import com.project.selim.footcalendar.data.FootRepository
import com.project.selim.footcalendar.data.models.Matches
import com.project.selim.footcalendar.data.network.ApiResult
import kotlinx.coroutines.*

class CalendarViewModel(private val repository: FootRepository) : ViewModel() {

    var matches = MutableLiveData<Matches>()

    val errorEvent = MutableLiveData<Event<String>>()

    private var myJob: Job? = null

    fun callNetwork() {
        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getMatches()
            withContext(Dispatchers.Main) {
                when (result) {
                    is ApiResult.Error -> setError(result.exception)
                    is ApiResult.Success -> setMatches(result.data)
                }

            }
        }
    }

    private fun setMatches(matches: Matches) {
        this.matches.value = matches
    }

    private fun setError(error: Throwable) {
        error.message?.let { message ->
            errorEvent.value = Event(message)
        }
    }
}