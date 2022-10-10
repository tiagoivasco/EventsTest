package com.ivasco.eventstest.data.di

import com.ivasco.eventstest.data.config.provideMyRetrofit
import com.ivasco.eventstest.data.endpoint.EventEndpoint
import com.ivasco.eventstest.data.error.NetworkErrorHandler
import com.ivasco.eventstest.data.service.EventService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single { EventService(provideEventService(get()), get<NetworkErrorHandler>()) }
    factory { NetworkErrorHandler() }
    single{ provideMyRetrofit(androidContext()) }
}

private fun provideEventService(retrofit: Retrofit) = retrofit.create(EventEndpoint::class.java)

