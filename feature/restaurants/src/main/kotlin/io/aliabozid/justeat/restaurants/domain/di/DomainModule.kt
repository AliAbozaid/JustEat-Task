package io.aliabozid.justeat.restaurants.domain.di

import io.aliabozid.justeat.restaurants.domain.FilterUseCase
import io.aliabozid.justeat.restaurants.domain.RestaurantChainUseCase
import io.aliabozid.justeat.restaurants.domain.RestaurantUseCase
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
			preferenceHelper = get(),
			sortUseCase = get(),
			filterUseCase = get(),
			dispatcher = get()
		)
	}

	single {
		SortUseCase()
	}
	single {
		FilterUseCase(dispatcher = get())
	}
}
