package io.aliabozid.justeat.restaurants.presentation

import io.aliabozid.justeat.restaurants.base.DispatcherImplTest
import io.aliabozid.justeat.restaurants.data.Resource
import io.aliabozid.justeat.restaurants.data.model.Restaurant
import io.aliabozid.justeat.restaurants.data.model.RestaurantStatus
import io.aliabozid.justeat.restaurants.data.model.SortOption
import io.aliabozid.justeat.restaurants.domain.RestaurantChainUseCase
import io.aliabozid.justeat.restaurants.domain.RestaurantUseCase
import io.aliabozid.justeat.restaurants.domain.SortUseCase
import io.aliabozid.justeat.sort.SelectedSort
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RestaurantViewModelTest {
	private lateinit var restaurantViewModel: RestaurantViewModel
	private val restaurantUseCase: RestaurantUseCase = mockk(relaxed = true)
	private val restaurantChainUseCase: RestaurantChainUseCase =
		mockk(relaxed = true)
	private val testDispatcher = DispatcherImplTest()

	private val restaurant = Restaurant(
		name = "Tanoshii Sushi",
		status = RestaurantStatus.OPEN,
		sortingValues = SortOption(
			bestMatch = 0.0,
			newest = 96.0,
			ratingAverage = 4.5,
			distance = 1190.0,
			popularity = 17.0,
			averageProductPrice = 1536.0,
			deliveryCosts = 200.0,
			minCost = 1000.0
		)
	)
	private val restaurants = mutableListOf<Restaurant>()

	@Before
	fun setUp() {
		restaurants.add(restaurant)
		coEvery { restaurantUseCase.getRestaurants() }.returns(
			Resource.Success(restaurants)
		)
		restaurantViewModel =
			RestaurantViewModel(
				restaurantUseCase,
				restaurantChainUseCase = restaurantChainUseCase,
				dispatcher = testDispatcher
			)
	}

	@Test
	fun `given getRestaurants when called from viewModel then get called from useCase`() {
		restaurantViewModel.getRestaurants()
		coVerify { restaurantUseCase.getRestaurants() }
		coVerify { restaurantChainUseCase.execute() }
	}

	@Test
	fun `given setSelectedSort when called from viewModel then call execute from useCase`() {
		restaurantViewModel.setSelectedSort(SelectedSort.BEST_MATCH)
		verify { restaurantChainUseCase.setSelectedSort(any()) }
		coVerify { restaurantChainUseCase.execute() }
	}

	@Test
	fun `given searchRestaurant when called from viewModel then call execute from useCase`() {
		restaurantViewModel.searchRestaurant("Ta")
		coVerify { restaurantChainUseCase.execute() }
	}
}
