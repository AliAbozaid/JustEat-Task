package io.aliabozid.justeat.restaurants.data.model

data class Restaurant(
	val name: String?,
	val status: RestaurantStatus?,
	val sortingValues: SortOption?
)
