package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.data.model.Restaurant
import io.aliabozid.justeat.restaurants.data.model.RestaurantStatus
import io.aliabozid.justeat.restaurants.data.model.SortOption
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FilterUseCaseTest {
    private lateinit var filterUseCase: FilterUseCase

    private val restaurant1 = Restaurant(
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
    private val restaurant2 = Restaurant(
        name = "BB",
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
        restaurants.add(restaurant1)
        restaurants.add(restaurant2)
        filterUseCase = FilterUseCase()
    }

    @Test
    fun `given filterRestaurant is called when filter is filled then data should be filtered`() {
        val restaurantsList = filterUseCase.filterRestaurant(restaurants, "a")

        Assert.assertEquals(1, restaurantsList.size)
        Assert.assertEquals("AAA", restaurantsList[0].name)
    }

    @Test
    fun `given filterRestaurant called when filter isn't filled then data should be filtered`() {
        val restaurantsList = filterUseCase.filterRestaurant(restaurants, null)

        Assert.assertEquals(2, restaurantsList.size)
        Assert.assertEquals("BB", restaurantsList[1].name)
    }
}
