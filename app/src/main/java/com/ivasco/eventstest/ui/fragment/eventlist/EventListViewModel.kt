package com.ivasco.eventstest.ui.fragment.eventlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivasco.eventstest.data.model.Event
import com.ivasco.eventstest.data.model.Result
import com.ivasco.eventstest.data.service.EventService
import com.ivasco.eventstest.ui.extensions.getMessageResource
import kotlinx.coroutines.launch

sealed class UiState {
    data class Success(val events: List<Event>) : UiState()
    data class Error(val messageResource: Int) : UiState()
    object Loading : UiState()
}

class EventListViewModel(
    private val service: EventService
) : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState>
        get() = _state

    fun fetchEvents() {
        _state.value = UiState.Loading
        viewModelScope.launch {
            when (val result = service.getEvents()) {
                is Result.Success -> _state.value = UiState.Success(result.data)
                is Result.Fail -> _state.value = UiState.Error(result.error.getMessageResource())
            }
        }
    }
}