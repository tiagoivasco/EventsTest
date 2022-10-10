package com.ivasco.eventstest.data.error

interface ErrorHandler {
    fun getError(throwable: Throwable): Error
}