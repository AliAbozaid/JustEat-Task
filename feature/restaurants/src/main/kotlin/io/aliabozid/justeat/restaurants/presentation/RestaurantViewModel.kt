package io.aliabozid.justeat.restaurants.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.aliabozid.justeat.assets.utils.Dispatcher
import io.aliabozid.justeat.restaurants.data.Resource
import io.aliabozid.justeat.restaurants.data.model.Restaurant
import io.aliabozid.justeat.restaurants.domain.RestaurantUseCase
import io.aliabozid.justeat.restaurants.presentation.utils.ResourceUi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RestaurantViewModel constructor(
	private val restaurantUseCase: RestaurantUseCase,
	private val dispatcher: Dispatcher
) : ViewModel() {

	private val _restaurantStateFlow =
		MutableSharedFlow<ResourceUi<List<Restaurant>>>()
	val restaurantStateFlow = _restaurantStateFlow.asSharedFlow()

	fun getRestaurants() {
		viewModelScope.launch(dispatcher.io()) {
			_restaurantStateFlow.emit(ResourceUi.loading())
			when (val restaurantResource = restaurantUseCase.getRestaurants()) {
				is Resource.Success -> {
					_restaurantStateFlow.emit(
						ResourceUi.success(
							restaurantResource.data
						)
					)
				}
				is Resource.Error -> {
					_restaurantStateFlow.emit(
						ResourceUi.error(
							Exception(
								restaurantResource.message
							)
						)
					)
				}
			}
		}
	}
}