package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.data.Resource
import io.aliabozid.justeat.restaurants.data.model.Restaurant
import io.aliabozid.justeat.restaurants.data.repository.RestaurantRepository

class SearchRestaurantUseCase constructor(
	private val restaurantRepository: RestaurantRepository
) {

	suspend fun getRestaurants(): Resource<List<Restaurant>> =
		restaurantRepository.getRestaurants()
}