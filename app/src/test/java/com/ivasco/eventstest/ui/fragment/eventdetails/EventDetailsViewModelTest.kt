package com.ivasco.eventstest.ui.fragment.eventdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ivasco.eventstest.data.error.NetworkError
import com.ivasco.eventstest.data.model.Event
import com.ivasco.eventstest.data.model.Person
import com.ivasco.eventstest.data.model.Result
import com.ivasco.eventstest.data.service.EventService
import com.ivasco.eventstest.ui.error.UiError
import com.ivasco.eventstest.ui.extensions.getMessageResource
import com.ivasco.eventstest.util.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

internal class EventDetailsViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Suppress("unused")
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val service: EventService = mockk()
    private val viewModel = EventDetailsViewModel(service)

    private val event = Event(
        id = 1L,
        title = "title",
        description = "description",
        image = "image",
        price = 2.0,
        date = 121212121,
        people = emptyList()
    )

    private val person = Person(
        name = "Joaquin",
        email = "emaildojoaquin@email.com"
    )

    @Test
    fun `Should set state as success when service checkIn is successful`() {
        coEvery { service.checkIn(any()) } returns Result.Success(Unit)

        viewModel.eventCheckIn(event, person)
        assertTrue(viewModel.state.value is UiState.Success)
    }

    @Test
    fun `Should set state as error with NetworkError message resource when service checkIn fail`() {
        coEvery { service.checkIn(any()) } returns Result.Fail(NetworkError.NotFound)

        viewModel.eventCheckIn(event, person)
        assertTrue(viewModel.state.value is UiState.Error)
        assertEquals(
            (viewModel.state.value as UiState.Error).messageResource,
            NetworkError.NotFound.getMessageResource()
        )
    }

    @Test
    fun `Should set state as error with UiError personNotFound message resource when person is null`() {
        viewModel.eventCheckIn(event, null)
        assertTrue(viewModel.state.value is UiState.Error)
        assertEquals(
            (viewModel.state.value as UiState.Error).messageResource,
            UiError.PersonNotFound.getMessageResource()
        )
    }
}