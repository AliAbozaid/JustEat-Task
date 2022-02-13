package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.data.Resource
import io.aliabozid.justeat.restaurants.data.model.Restaurant
import io.aliabozid.justeat.restaurants.data.model.RestaurantStatus
import io.aliabozid.justeat.restaurants.data.model.SortOption
import io.aliabozid.justeat.restaurants.data.repository.RestaurantRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class RestaurantUseCaseTest {
	private lateinit var restaurantUseCase: RestaurantUseCase
	private val restaurantRepository: RestaurantRepository =
		mockk(relaxed = true)

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
		coEvery { restaurantRepository.getRestaurants() }.returns(
			Resource.Success(restaurants)
		)
		restaurantUseCase = RestaurantUseCase(restaurantRepository)
	}

	@Test
	fun `given getRestaurants when called from useCase then getRestaurants get called from repo`() =
		runBlockingTest {
			restaurantUseCase.getRestaurants()
			coVerify {
				restaurantRepository.getRestaurants()
			}
		}
}
