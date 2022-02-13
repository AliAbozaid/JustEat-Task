package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.assets.utils.Dispatcher
import io.aliabozid.justeat.restaurants.data.model.Restaurant
import kotlinx.coroutines.withContext

class FilterUseCase(
    private val dispatcher: Dispatcher
) {

    suspend fun filterRestaurant(
        restaurants: List<Restaurant>,
        restaurantName: String?
    ): MutableList<Restaurant> {
        return withContext(dispatcher.io()) {
            if (restaurantName == null) return@withContext restaurants.toMutableList()
            val updatedMapHotelsList = restaurants.filter { restaurant ->
                restaurant.name?.contains(
                    restaurantName,
                    true
                ) ?: false
            }
            updatedMapHotelsList.toMutableList()
        }
    }
}
