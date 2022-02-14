package io.aliabozid.justeat.restaurants.domain.model

data class Restaurant(
    val name: String?,
    val status: RestaurantStatus?,
    val sortingValues: SortOption?
)
