package io.aliabozid.justeat.restaurants.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.aliabozid.justeat.assets.utils.Dispatcher
import io.aliabozid.justeat.assets.utils.ResourceUi
import io.aliabozid.justeat.restaurants.data.Resource
import io.aliabozid.justeat.restaurants.domain.RestaurantChainUseCase
import io.aliabozid.justeat.restaurants.domain.RestaurantUseCase
import io.aliabozid.justeat.restaurants.domain.SortSelectionUseCase
import io.aliabozid.justeat.restaurants.domain.model.Restaurant
import io.aliabozid.justeat.sort.SelectedSort
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RestaurantViewModel constructor(
    private val restaurantUseCase: RestaurantUseCase,
    private val restaurantChainUseCase: RestaurantChainUseCase,
    private val sortSelectionUseCase: SortSelectionUseCase,
    private val dispatcher: Dispatcher
) : ViewModel() {

    private val _restaurantStateFlow =
        MutableSharedFlow<ResourceUi<List<Restaurant>>>()
    val restaurantStateFlow = _restaurantStateFlow.asSharedFlow()

    private val _selectedSortLiveData: MutableLiveData<SelectedSort> = MutableLiveData()
    val selectedSortLiveData: LiveData<SelectedSort> = _selectedSortLiveData

    var selectedSortItem = SelectedSort.BEST_MATCH

    init {
        selectedSort()
        getRestaurants()
    }

    private fun selectedSort() {
        viewModelScope.launch(dispatcher.io()) {
            sortSelectionUseCase.getSelectedSort().collect {
                _selectedSortLiveData.postValue(it)
            }
        }
    }

    fun setSelectedSort(selectedSort: SelectedSort) {
        viewModelScope.launch(dispatcher.io()) {
            sortSelectionUseCase.setSelectedSort(selectedSort)
            submitSuccessData()
        }
    }

    private fun getRestaurants() {
        viewModelScope.launch(dispatcher.io()) {
            _restaurantStateFlow.emit(ResourceUi.loading())
            when (val restaurantResource = restaurantUseCase.getRestaurants()) {
                is Resource.Success -> {
                    restaurantChainUseCase.restaurants =
                        restaurantResource.data.toMutableList()
                    submitSuccessData()
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

    fun searchRestaurant(restaurantName: String) {
        restaurantChainUseCase.restaurantName = restaurantName
        submitSuccessData()
    }

    private fun submitSuccessData() {
        viewModelScope.launch(dispatcher.io()) {
            _restaurantStateFlow.emit(
                ResourceUi.success(
                    restaurantChainUseCase.execute(selectedSortItem)
                )
            )
        }
    }
}
