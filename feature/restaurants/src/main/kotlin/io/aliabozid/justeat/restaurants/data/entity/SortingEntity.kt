package io.aliabozid.justeat.restaurants.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SortingEntity(
    val bestMatch: Double?,
    val newest: Double?,
    val ratingAverage: Double?,
    val distance: Double?,
    val popularity: Double?,
    val averageProductPrice: Double?,
    val deliveryCosts: Double?,
    val minCost: Double?,
)
