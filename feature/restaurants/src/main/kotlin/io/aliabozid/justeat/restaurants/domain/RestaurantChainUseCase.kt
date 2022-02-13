package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.assets.utils.Dispatcher
import io.aliabozid.justeat.restaurants.data.helper.PreferenceHelper
import io.aliabozid.justeat.restaurants.data.model.Restaurant
import io.aliabozid.justeat.restaurants.data.model.RestaurantStatus
import io.aliabozid.justeat.sort.SelectedSort
import kotlinx.coroutines.withContext

class RestaurantChainUseCase constructor(
	private val preferenceHelper: PreferenceHelper,
	private val sortUseCase: SortUseCase,
	private val filterUseCase: FilterUseCase,
	private val dispatcher: Dispatcher
) {
	var restaurants = mutableListOf<Restaurant>()
	var restaurantName: String? = null

	fun getSelectedSort() = preferenceHelper.selectedSort

	fun setSelectedSort(selectedSort: SelectedSort) {
		preferenceHelper.selectedSort = selectedSort
	}

	suspend fun execute(): MutableList<Restaurant> =
		withContext(dispatcher.io()) {
			val restaurantResult = if (restaurantName.isNullOrEmpty().not()) {
				filterUseCase.filterRestaurant(restaurants, restaurantName)
			} else {
				restaurants
			}
			sortUseCase.sortRestaurant(getSelectedSort(), restaurantResult)
			return@withContext separateStatus(restaurantResult)
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