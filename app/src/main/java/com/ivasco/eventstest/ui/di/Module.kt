package com.ivasco.eventstest.ui.di

import com.ivasco.eventstest.ui.fragment.entry.EntryViewModel
import com.ivasco.eventstest.ui.fragment.eventdetails.EventDetailsViewModel
import com.ivasco.eventstest.ui.fragment.eventlist.EventListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { EntryViewModel() }
    viewModel { EventDetailsViewModel(get()) }
    viewModel { EventListViewModel(get()) }
}