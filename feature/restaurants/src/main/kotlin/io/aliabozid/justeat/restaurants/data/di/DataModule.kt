package io.aliabozid.justeat.restaurants.data.di

import android.content.Context
import com.squareup.moshi.Moshi
import io.aliabozid.justeat.assets.utils.Constant
import io.aliabozid.justeat.restaurants.data.client.RestaurantsLocalClient
import io.aliabozid.justeat.restaurants.data.filereader.FileReader
import io.aliabozid.justeat.restaurants.data.filereader.FileReaderImpl
import io.aliabozid.justeat.restaurants.data.helper.PreferenceHelper
import io.aliabozid.justeat.restaurants.data.repository.RestaurantRepository
import io.aliabozid.justeat.restaurants.data.repository.RestaurantRepositoryImpl
import io.aliabozid.justeat.restaurants.data.repository.RestaurantsErrorManager
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<Moshi> {
        Moshi.Builder().build()
    }

    single<FileReader> {
        FileReaderImpl(
            context = androidApplication()
        )
    }

    single {
        RestaurantsLocalClient(
            moshi = get(),
            fileReader = get()
        )
    }

    single {
        RestaurantsErrorManager(
            context = androidApplication()
        )
    }

    single<RestaurantRepository> {
        RestaurantRepositoryImpl(
            restaurantsLocalClient = get(),
            errorManager = get()
        )
    }

    factory {
        androidContext()
            .getSharedPreferences(
                Constant.Preference.SHARED_PREF_NAME,
                Context.MODE_PRIVATE
            )
    }

    single { PreferenceHelper(get()) }
}
