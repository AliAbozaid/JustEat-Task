package io.aliabozid.justeat.restaurants.data.di

import com.squareup.moshi.Moshi
import io.aliabozid.justeat.restaurants.data.client.RestaurantsLocalClient
import io.aliabozid.justeat.restaurants.data.filereader.FileReader
import io.aliabozid.justeat.restaurants.data.filereader.FileReaderImpl
import io.aliabozid.justeat.restaurants.data.repository.RestaurantRepositoryImpl
import io.aliabozid.justeat.restaurants.data.repository.RestaurantsErrorManager
import io.aliabozid.justeat.restaurants.data.repository.SelectedSortRepositoryImpl
import io.aliabozid.justeat.restaurants.domain.repo.RestaurantRepository
import io.aliabozid.justeat.restaurants.domain.repo.SelectedSortRepository
import org.koin.android.ext.koin.androidApplication
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
    single<SelectedSortRepository> {
        SelectedSortRepositoryImpl(
            context = androidApplication()
        )
    }
}
