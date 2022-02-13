package io.aliabozid.justeat.restaurants.data.model

import io.aliabozid.justeat.sort.SelectedSort

data class SortOption(
    val bestMatch: Double?,
    val newest: Double?,
    val ratingAverage: Double?,
    val distance: Double?,
    val popularity: Double?,
    val averageProductPrice: Double?,
    val deliveryCosts: Double?,
    val minCost: Double?
) {

    fun getSortType(option: SelectedSort) =
        when (option) {
            SelectedSort.BEST_MATCH -> bestMatch
            SelectedSort.NEWEST ->
                newest

            SelectedSort.RATING_AVERAGE ->
                ratingAverage

            SelectedSort.DISTANCE ->
                distance

            SelectedSort.POPULARITY ->
                popularity

            SelectedSort.AVERAGE_PRODUCT_PRICE ->
                averageProductPrice

            SelectedSort.DELIVERY_COSTS ->
                deliveryCosts

            SelectedSort.MIN_COST ->
                minCost
        }
}
