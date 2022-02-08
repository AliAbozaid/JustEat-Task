package io.aliabozid.justeat.restaurants.data.repository

import io.aliabozid.justeat.restaurants.data.Resource
import io.aliabozid.justeat.restaurants.data.client.FileResponse
import io.aliabozid.justeat.restaurants.data.client.RestaurantsLocalClient
import io.aliabozid.justeat.restaurants.data.mapper.mapToRestaurant
import io.aliabozid.justeat.restaurants.data.model.Restaurant

class RestaurantRepositoryImpl constructor(
    private val restaurantsLocalClient: RestaurantsLocalClient,
    private val errorManager: RestaurantsErrorManager,
) : RestaurantRepository {

    override suspend fun getRestaurants(): Resource<List<Restaurant>> =
        when (val response = restaurantsLocalClient.getRestaurants()) {
            is FileResponse.FileText -> {
                Resource.Success(
                    response.data.map { restaurantEntity ->
                        restaurantEntity.mapToRestaurant()
                    }
                )
            }
            is FileResponse.Error -> {
                Resource.Error(
                    errorManager.getErrorMessage(response.cause),
                    response.cause
                )
            }
        }
}
