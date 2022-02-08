package io.aliabozid.justeat.restaurants.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestaurantEntity(
    val name: String?,
    val status: String?,
    val sortingValues: SortingEntity?
)
