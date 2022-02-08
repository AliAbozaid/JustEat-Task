package io.aliabozid.justeat.restaurants.data.model

data class SortOption(
    val bestMatch: Double?,
    val newest: Double?,
    val ratingAverage: Double?,
    val distance: Double?,
    val popularity: Double?,
    val averageProductPrice: Double?,
    val deliveryCosts: Double?,
    val minCost: Double?
)
