package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.domain.model.Restaurant

class FilterUseCase {

    fun filterRestaurant(
        restaurants: List<Restaurant>,
        restaurantName: String?
    ): MutableList<Restaurant> {
        if (restaurantName == null) return restaurants.toMutableList()
        val updatedMapHotelsList = restaurants.filter { restaurant ->
            restaurant.name?.contains(
                restaurantName,
                true
            ) ?: false
        }
        return updatedMapHotelsList.toMutableList()
    }
}
