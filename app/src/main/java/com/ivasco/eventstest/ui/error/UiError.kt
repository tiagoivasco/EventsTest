package com.ivasco.eventstest.ui.error

import com.ivasco.eventstest.data.error.Error

sealed class UiError : Error {
    object PersonNotFound : UiError()
}
