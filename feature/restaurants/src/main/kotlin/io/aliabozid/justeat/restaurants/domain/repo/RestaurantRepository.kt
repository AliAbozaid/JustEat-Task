package io.aliabozid.justeat.restaurants.domain.repo

import io.aliabozid.justeat.restaurants.data.Resource
import io.aliabozid.justeat.restaurants.domain.model.Restaurant

interface RestaurantRepository {

    suspend fun getRestaurants(): Resource<List<Restaurant>>
}
