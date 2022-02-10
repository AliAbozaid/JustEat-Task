package io.aliabozid.justeat.restaurants.presentation

import io.aliabozid.justeat.restaurants.base.DispatcherImplTest
import io.aliabozid.justeat.restaurants.domain.RestaurantUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class RestaurantViewModelTest {
	private lateinit var restaurantViewModel: RestaurantViewModel
	private val restaurantUseCase: RestaurantUseCase = mockk(relaxed = true)
	private val testDispatcher = DispatcherImplTest()

	@Before
	fun setUp() {
		restaurantViewModel =
			RestaurantViewModel(restaurantUseCase, testDispatcher)
	}

	@Test
	fun `given getRestaurants when called from viewModel then getRestaurants get called from useCase`() {
		restaurantViewModel.getRestaurants()
		coEvery { restaurantUseCase.getRestaurants() }
	}
}