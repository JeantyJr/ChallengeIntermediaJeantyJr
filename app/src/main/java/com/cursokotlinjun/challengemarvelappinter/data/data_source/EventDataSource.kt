package com.cursokotlinjun.challengemarvelappinter.data.data_source

import com.cursokotlinjun.challengemarvelappinter.data.service.EventAPI
import javax.inject.Inject

class EventDataSource @Inject constructor(
    private val eventAPI: EventAPI
): MarvelDataSource() {

    suspend fun getAllEvents(auth: HashMap<String, String>, orderBy: String, limit: Int) =
        getResult { eventAPI.getAllEvents(auth, orderBy, limit) }

    suspend fun getAllComicsEvents(eventId:Int, auth: HashMap<String, String>, limit: Int) =
        getResult { eventAPI.getAllComicsEvents(eventId, auth, limit) }
}