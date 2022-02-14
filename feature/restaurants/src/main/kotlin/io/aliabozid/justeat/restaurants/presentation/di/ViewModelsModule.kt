package io.aliabozid.justeat.restaurants.presentation.di

import io.aliabozid.justeat.restaurants.presentation.RestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        RestaurantViewModel(
            restaurantUseCase = get(),
            sortSelectionUseCase = get(),
            restaurantChainUseCase = get(),
            dispatcher = get()
        )
    }
}
