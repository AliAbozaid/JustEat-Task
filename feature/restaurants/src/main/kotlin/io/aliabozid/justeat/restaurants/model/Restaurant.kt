package io.aliabozid.justeat.restaurants.model

data class Restaurant(
	val name: String?,
	val status: RestaurantStatus?,
	val sortingValues: SortOption?
)
