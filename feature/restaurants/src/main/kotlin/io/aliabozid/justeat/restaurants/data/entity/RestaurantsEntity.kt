package io.aliabozid.justeat.restaurants.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestaurantsEntity(
    val restaurants: List<RestaurantEntity>
)
