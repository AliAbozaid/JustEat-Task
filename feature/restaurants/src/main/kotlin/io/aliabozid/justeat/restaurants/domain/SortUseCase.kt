package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.domain.model.Restaurant
import io.aliabozid.justeat.sort.SelectedSort

class SortUseCase {

    fun sortRestaurant(
        option: SelectedSort,
        restaurants: MutableList<Restaurant>
    ) {
        when (option) {
            SelectedSort.BEST_MATCH -> restaurants.sortByDescending {
                it.sortingValues?.bestMatch
            }
            SelectedSort.NEWEST -> restaurants.sortByDescending {
                it.sortingValues?.newest
            }
            SelectedSort.RATING_AVERAGE -> restaurants.sortByDescending {
                it.sortingValues?.ratingAverage
            }
            SelectedSort.DISTANCE -> restaurants.sortBy {
                it.sortingValues?.distance
            }
            SelectedSort.POPULARITY -> restaurants.sortByDescending {
                it.sortingValues?.popularity
            }
            SelectedSort.AVERAGE_PRODUCT_PRICE -> restaurants.sortBy {
                it.sortingValues?.averageProductPrice
            }
            SelectedSort.DELIVERY_COSTS -> restaurants.sortBy {
                it.sortingValues?.deliveryCosts
            }
            SelectedSort.MIN_COST -> restaurants.sortBy {
                it.sortingValues?.minCost
            }
        }
    }
}
