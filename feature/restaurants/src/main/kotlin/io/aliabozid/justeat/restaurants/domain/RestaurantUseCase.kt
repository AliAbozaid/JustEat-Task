package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.data.Resource
import io.aliabozid.justeat.restaurants.domain.model.Restaurant
import io.aliabozid.justeat.restaurants.domain.repo.RestaurantRepository

class RestaurantUseCase constructor(
    private val restaurantRepository: RestaurantRepository
) {

    suspend fun getRestaurants(): Resource<List<Restaurant>> =
        restaurantRepository.getRestaurants()
}
