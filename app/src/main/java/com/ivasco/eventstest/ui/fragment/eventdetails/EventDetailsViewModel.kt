package com.ivasco.eventstest.ui.fragment.eventdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivasco.eventstest.data.model.Event
import com.ivasco.eventstest.data.model.EventPerson
import com.ivasco.eventstest.data.model.Person
import com.ivasco.eventstest.data.model.Result
import com.ivasco.eventstest.data.service.EventService
import com.ivasco.eventstest.ui.error.UiError
import com.ivasco.eventstest.ui.extensions.getMessageResource
import kotlinx.coroutines.launch

sealed class UiState {
    object Success : UiState()
    data class Error(val messageResource: Int) : UiState()
}

class EventDetailsViewModel(
    private val service: EventService
) : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState>
        get() = _state

    fun eventCheckIn(event: Event, person: Person?) {
        viewModelScope.launch {
            if (person != null) {
                _state.value =
                    when (val result = service.checkIn(EventPerson.toEventPerson(event, person))) {
                        is Result.Success -> UiState.Success
                        is Result.Fail -> UiState.Error(result.error.getMessageResource())
                    }
            } else {
                _state.value = UiState.Error(UiError.PersonNotFound.getMessageResource())
            }
        }
    }
}