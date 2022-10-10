package com.ivasco.eventstest.data.model

import com.ivasco.eventstest.data.error.Error

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Fail<T>(val error: Error) : Result<T>()
}