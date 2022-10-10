package com.ivasco.eventstest.ui.fragment.entry

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class EntryViewModelTest {
    private val viewModel = EntryViewModel()

    @Test
    fun shouldReturnTrueWhenNameIsEmpty() {
        assertTrue(viewModel.isNameInvalid(""))
    }

    @Test
    fun shouldReturnTrueWhenNameIsOnlySpaces() {
        assertTrue(viewModel.isNameInvalid("  "))
    }

    @Test
    fun shouldReturnTrueWhenNameIsNull() {
        assertTrue(viewModel.isNameInvalid(null))
    }

    @Test
    fun shouldReturnFalseWhenNameIsntEmpty() {
        assertFalse(viewModel.isNameInvalid("Not empty"))
    }

    @Test
    fun shouldReturnTrueWhenEmailIsEmpty() {
        assertTrue(viewModel.isEmailInvalid(""))
    }

    @Test
    fun shouldReturnTrueWhenEmailIsOnlySpaces() {
        assertTrue(viewModel.isEmailInvalid("  "))
    }

    @Test
    fun shouldReturnTrueWhenEmailIsNull() {
        assertTrue(viewModel.isEmailInvalid(null))
    }

    @Test
    fun shouldReturnTrueWhenEmailIsntValid() {
        var invalidEmail = "email";
        assertTrue(viewModel.isEmailInvalid(invalidEmail))

        invalidEmail = "email@"
        assertTrue(viewModel.isEmailInvalid(invalidEmail))

        invalidEmail = "email@invalid"
        assertTrue(viewModel.isEmailInvalid(invalidEmail))

        invalidEmail = "email@invalid."
        assertTrue(viewModel.isEmailInvalid(invalidEmail))
    }
}