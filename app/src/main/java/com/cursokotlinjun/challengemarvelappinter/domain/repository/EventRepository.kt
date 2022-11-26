package com.cursokotlinjun.challengemarvelappinter.domain.repository

import com.cursokotlinjun.challengemarvelappinter.data.data_source.Ressource
import com.cursokotlinjun.challengemarvelappinter.data.data_source.dto.Data
import com.cursokotlinjun.challengemarvelappinter.domain.model.Comic
import com.cursokotlinjun.challengemarvelappinter.domain.model.Events

interface EventRepository {
    suspend fun getAllEvents(
        orderBy: String = "startDate",
        limit: Int = 15
    ): Ressource<List<Events>>

    suspend fun getAllComicsEvents(
        eventId: Int,
        limit: Int = 5
    ):Ressource<List<Comic>>
}