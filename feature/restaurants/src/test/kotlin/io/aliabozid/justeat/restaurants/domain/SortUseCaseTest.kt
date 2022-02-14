package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.domain.model.Restaurant
import io.aliabozid.justeat.restaurants.domain.model.RestaurantStatus
import io.aliabozid.justeat.restaurants.domain.model.SortOption
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SortUseCaseTest {
    private lateinit var sortUseCase: SortUseCase

    @Before
    fun setUp() {
        sortUseCase = SortUseCase()
    }

    // sort by descending
    @Test
    fun `given sort is called when sort by favourite then the data should be sorted`() {
        val restaurantsList = mutableListOf<Restaurant>()
        val restaurantsModel1 = Restaurant(
            name = "Restaurant1",
            status = RestaurantStatus.OPEN,
            sortingValues = SortOption(
                bestMatch = 300.0,
                newest = null,
                ratingAverage = null,
                distance = null,
                popularity = null,
                averageProductPrice = null,
                deliveryCosts = null,
                minCost = null
            )
        )
        val restaurantsModel2 = Restaurant(
            name = "Restaurant2",
            status = RestaurantStatus.OPEN,
            sortingValues = SortOption(
                bestMatch = 350.0,
                newest = null,
                ratingAverage = null,
                distance = null,
                popularity = null,
                averageProductPrice = null,
                deliveryCosts = null,
                minCost = null
            )
        )

        restaurantsList.add(restaurantsModel1)
        restaurantsList.add(restaurantsModel2)

        sortUseCase.sortRestaurant(
            io.aliabozid.justeat.sort.SelectedSort.BEST_MATCH,
            restaurantsList
        )

        Assert.assertEquals("Restaurant2", restaurantsList[0].name)
    }

    // sort by ascending
    @Test
    fun `given sort is called when sort by distance then the data should be sorted`() {
        val restaurantsList = mutableListOf<Restaurant>()
        val restaurantsModel1 = Restaurant(
            name = "Restaurant1",
            status = RestaurantStatus.OPEN,
            sortingValues = SortOption(
                bestMatch = null,
                newest = null,
                ratingAverage = null,
                distance = 111.0,
                popularity = null,
                averageProductPrice = null,
                deliveryCosts = null,
                minCost = null
            )
        )
        val restaurantsModel2 = Restaurant(
            name = "Restaurant2",
            status = RestaurantStatus.OPEN,
            sortingValues = SortOption(
                bestMatch = null,
                newest = null,
                ratingAverage = null,
                distance = 120.0,
                popularity = null,
                averageProductPrice = null,
                deliveryCosts = null,
                minCost = null
            )
        )
        val restaurantsModel3 = Restaurant(
            name = "Restaurant3",
            status = RestaurantStatus.OPEN,
            sortingValues = SortOption(
                bestMatch = null,
                newest = null,
                ratingAverage = null,
                distance = 20.0,
                popularity = null,
                averageProductPrice = null,
                deliveryCosts = null,
                minCost = null
            )
        )

        restaurantsList.add(restaurantsModel1)
        restaurantsList.add(restaurantsModel2)
        restaurantsList.add(restaurantsModel3)

        sortUseCase.sortRestaurant(
            io.aliabozid.justeat.sort.SelectedSort.DISTANCE,
            restaurantsList
        )

        Assert.assertEquals("Restaurant3", restaurantsList[0].name)
    }
}
