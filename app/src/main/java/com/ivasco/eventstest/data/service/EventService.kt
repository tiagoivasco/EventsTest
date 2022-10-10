package com.ivasco.eventstest.data.service

import com.ivasco.eventstest.data.endpoint.EventEndpoint
import com.ivasco.eventstest.data.error.ErrorHandler
import com.ivasco.eventstest.data.model.Event
import com.ivasco.eventstest.data.model.EventPerson
import com.ivasco.eventstest.data.model.Result

class EventService(
    private val endpoint: EventEndpoint,
    private val errorHandler: ErrorHandler
) {
    suspend fun getEvents(): Result<List<Event>> {
        return wrapInResult { endpoint.getEvents() }
    }

    suspend fun getEventDetails(id: Long): Result<Event> {
        return wrapInResult { endpoint.getEventDetails(id) }
    }

    suspend fun checkIn(eventPerson: EventPerson): Result<Unit> {
        return wrapInResult { endpoint.checkIn(eventPerson) }
    }

    private suspend fun <T> wrapInResult(block: suspend () -> T): Result<T> {
        return try {
            Result.Success(block())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Fail(errorHandler.getError(e))
        }
    }
}