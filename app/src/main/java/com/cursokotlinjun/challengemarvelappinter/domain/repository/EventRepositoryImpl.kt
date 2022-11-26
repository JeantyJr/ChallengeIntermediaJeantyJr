package com.cursokotlinjun.challengemarvelappinter.domain.repository

import com.cursokotlinjun.challengemarvelappinter.data.data_source.EventDataSource

import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventDataSource: EventDataSource
): MarvelRepository(), EventRepository  {

    override suspend fun getAllEvents(orderBy: String, limit: Int) =
        eventDataSource.getAllEvents(authParams.getMap(), orderBy, limit)

    override suspend fun getAllComicsEvents(eventId: Int, limit: Int ) =
        eventDataSource.getAllComicsEvents(eventId, authParams.getMap(), limit)
    }
