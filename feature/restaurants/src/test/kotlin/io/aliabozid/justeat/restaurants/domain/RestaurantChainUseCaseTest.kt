package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.domain.model.Restaurant
import io.aliabozid.justeat.restaurants.domain.model.RestaurantStatus
import io.aliabozid.justeat.restaurants.domain.model.SortOption
import io.aliabozid.justeat.sort.SelectedSort
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class RestaurantChainUseCaseTest {

    private val sortUseCase: SortUseCase = mockk(relaxed = true)
    private val filterUseCase: FilterUseCase = mockk(relaxed = true)
    private lateinit var restaurantChainUseCase: RestaurantChainUseCase

    private val restaurant = Restaurant(
        name = "AAA",
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

    @Before
    fun setUp() {
        restaurantChainUseCase = RestaurantChainUseCase(
            sortUseCase, filterUseCase
        )
        restaurantChainUseCase.restaurants.add(restaurant)
    }

    @Test
    fun `given execute() is called when filter is filled then filterRestaurant should be called`() =
        runBlockingTest {
            restaurantChainUseCase.restaurantName = "A"
            coEvery {
                filterUseCase.filterRestaurant(any(), any())
            } returns restaurantChainUseCase.restaurants

            restaurantChainUseCase.execute(SelectedSort.BEST_MATCH)
            coVerify {
                filterUseCase.filterRestaurant(any(), any())
            }
            coVerify {
                sortUseCase.sortRestaurant(any(), any())
            }
        }

    @Test
    fun `given execute() is called when filter is not filled then sort only should be called`() =
        runBlockingTest {
            restaurantChainUseCase.execute(SelectedSort.BEST_MATCH)
            coVerify {
                sortUseCase.sortRestaurant(any(), any())
            }
        }
}
