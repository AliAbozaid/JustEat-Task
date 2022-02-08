package io.aliabozid.justeat.restaurants.data.repository

import io.aliabozid.justeat.restaurants.data.Resource
import io.aliabozid.justeat.restaurants.data.model.Restaurant

interface RestaurantRepository {

    suspend fun getRestaurants(): Resource<List<Restaurant>>
}
