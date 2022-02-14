package io.aliabozid.justeat.restaurants.data.repo

import io.aliabozid.justeat.restaurants.data.client.FileResponse
import io.aliabozid.justeat.restaurants.data.client.RestaurantsLocalClient
import io.aliabozid.justeat.restaurants.data.entity.RestaurantEntity
import io.aliabozid.justeat.restaurants.data.entity.RestaurantsEntity
import io.aliabozid.justeat.restaurants.data.entity.SortingEntity
import io.aliabozid.justeat.restaurants.data.repository.RestaurantRepositoryImpl
import io.aliabozid.justeat.restaurants.data.repository.RestaurantsErrorManager
import io.aliabozid.justeat.restaurants.domain.repo.RestaurantRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class RestaurantRepositoryTest {
    private lateinit var restaurantRepository: RestaurantRepository
    private val restaurantsLocalClient: RestaurantsLocalClient =
        mockk(relaxed = true)
    private val errorManager: RestaurantsErrorManager = mockk(relaxed = true)
    lateinit var restaurantsEntity: RestaurantsEntity
    private val restaurantEntity = RestaurantEntity(
        name = "Tanoshii Sushi",
        status = "open",
        sortingValues = SortingEntity(
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
        val restaurants = mutableListOf<RestaurantEntity>()
        restaurants.add(restaurantEntity)
        restaurantsEntity = RestaurantsEntity(
            restaurants = restaurants
        )
        every { restaurantsLocalClient.getRestaurants() }.returns(
            FileResponse.FileText(restaurantsEntity.restaurants)
        )
        restaurantRepository =
            RestaurantRepositoryImpl(restaurantsLocalClient, errorManager)
    }

    @Test
    fun `given getRestaurants when called from repo then getRestaurants get called from client`() =
        runBlockingTest {
            restaurantRepository.getRestaurants()
            coVerify {
                restaurantsLocalClient.getRestaurants()
            }
        }
}
