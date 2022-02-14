package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.domain.model.Restaurant
import io.aliabozid.justeat.restaurants.domain.model.RestaurantStatus
import io.aliabozid.justeat.sort.SelectedSort

class RestaurantChainUseCase constructor(
    private val sortUseCase: SortUseCase,
    private val filterUseCase: FilterUseCase
) {
    var restaurants = mutableListOf<Restaurant>()
    var restaurantName: String? = null

    fun execute(selectedSort: SelectedSort): MutableList<Restaurant> {
        val restaurantResult = if (restaurantName.isNullOrEmpty().not()) {
            filterUseCase.filterRestaurant(restaurants, restaurantName)
        } else {
            restaurants
        }
        sortUseCase.sortRestaurant(selectedSort, restaurantResult)
        return separateStatus(restaurantResult)
    }

    private fun separateStatus(restaurants: MutableList<Restaurant>): MutableList<Restaurant> {
        val result = mutableListOf<Restaurant>()
        val groups = restaurants.groupBy { it.status }
        groups[RestaurantStatus.OPEN]?.toList()?.let { result.addAll(it) }
        groups[RestaurantStatus.ORDER_AHEAD]?.toList()
            ?.let { result.addAll(it) }
        groups[RestaurantStatus.CLOSED]?.toList()?.let { result.addAll(it) }
        return result
    }
}
