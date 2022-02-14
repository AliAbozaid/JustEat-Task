package io.aliabozid.justeat.restaurants.domain.di

import io.aliabozid.justeat.restaurants.domain.FilterUseCase
import io.aliabozid.justeat.restaurants.domain.RestaurantChainUseCase
import io.aliabozid.justeat.restaurants.domain.RestaurantUseCase
import io.aliabozid.justeat.restaurants.domain.SortSelectionUseCase
import io.aliabozid.justeat.restaurants.domain.SortUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        RestaurantUseCase(
            restaurantRepository = get()
        )
    }
    single {
        RestaurantChainUseCase(
            sortUseCase = get(),
            filterUseCase = get()
        )
    }

    single {
        SortSelectionUseCase(
            selectedSortRepository = get()
        )
    }

    single {
        SortUseCase()
    }
    single {
        FilterUseCase()
    }
}
