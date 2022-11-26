package com.cursokotlinjun.challengemarvelappinter.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlinjun.challengemarvelappinter.data.data_source.Ressource
import com.cursokotlinjun.challengemarvelappinter.domain.model.Comic
import com.cursokotlinjun.challengemarvelappinter.domain.model.ComicData
import com.cursokotlinjun.challengemarvelappinter.domain.model.EventDato
import com.cursokotlinjun.challengemarvelappinter.domain.model.Events
import com.cursokotlinjun.challengemarvelappinter.domain.repository.EventRepository
import com.cursokotlinjun.challengemarvelappinter.domain.repository.EventRepositoryImpl
import com.cursokotlinjun.challengemarvelappinter.util.extensions.toEventDatoList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepositoryImpl
): ViewModel() {
    private val _eventsStatus = MutableLiveData<EventsStatus>()
    val eventsStatus: LiveData<EventsStatus> = _eventsStatus
    private var mEventId = -1


    init {
        getEvents()
    }

    private fun getEvents(){
        _eventsStatus.value = EventsStatus.Loading(true)
        viewModelScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){
                eventRepository.getAllEvents("startDate", 15)
            }.run {
                when(status){
                    Ressource.Status.SUCCESS ->{
                        _eventsStatus.value = EventsStatus.Loading(false)
                        _eventsStatus.value = this.data?.let {
                            EventsStatus.Success(it.toEventDatoList())
                        }

                    }
                          Ressource.Status.ERROR -> {
                              _eventsStatus.value = EventsStatus.Loading(false)
                              _eventsStatus.value = EventsStatus.Error(true)
                          }
                        }
                    }
                }
    }





    fun getComics(event: EventDato) {
        if (event.id != mEventId && event.isVisible) {
            _eventsStatus.value = EventsStatus.Loading(true)
            viewModelScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    eventRepository.getAllComicsEvents(event.id)
                }.run {
                    when (status) {
                        Ressource.Status.SUCCESS -> {
                            _eventsStatus.value = EventsStatus.Loading(false)
                            _eventsStatus.value = this.data?.let {
                                EventsStatus.ComicSuccess(
                                    it.ifEmpty {
                                        listOf(
                                            Comic(
                                                -1,
                                                "Sorry! no comics yet",
                                                listOf(ComicData("", ""))
                                            )
                                        )
                                    }
                                )
                            }
                            mEventId = event.id
                        }
                        Ressource.Status.ERROR -> {
                            _eventsStatus.value = EventsStatus.Loading(false)
                            _eventsStatus.value = EventsStatus.Error(true)
                        }
                    }
                }
            }
        }

    }
        fun retry() = getEvents()
   sealed class EventsStatus{
        data class Success(val events: List<EventDato>) : EventsStatus()
        data class Loading(val isLoading: Boolean) : EventsStatus()
        data class Error(val isError: Boolean) : EventsStatus()
        data class ComicSuccess(val comics: List<Comic>) : EventsStatus()
    }


}