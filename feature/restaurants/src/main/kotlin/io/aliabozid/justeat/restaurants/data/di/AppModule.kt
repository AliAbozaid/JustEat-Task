package io.aliabozid.justeat.restaurants.data.di

import io.aliabozid.justeat.assets.utils.Dispatcher
import io.aliabozid.justeat.assets.utils.DispatcherImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { DispatcherImpl() } bind Dispatcher::class
}
