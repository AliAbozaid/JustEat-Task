package io.aliabozid.justeat.restaurants.data.client

import com.squareup.moshi.Moshi
import io.aliabozid.justeat.restaurants.data.entity.RestaurantEntity
import io.aliabozid.justeat.restaurants.data.entity.RestaurantsEntity
import io.aliabozid.justeat.restaurants.data.entity.SortingEntity
import io.aliabozid.justeat.restaurants.data.filereader.FileReader
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class RestaurantsLocalClientTest {

    private lateinit var restaurantsLocalClient: RestaurantsLocalClient
    private val moshi = Moshi.Builder().build()
    private val fileReader: FileReader = mockk(relaxed = true)
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
    private lateinit var jsonValue: String

    @Before
    fun setUp() {
        val restaurants = mutableListOf<RestaurantEntity>()
        restaurants.add(restaurantEntity)
        restaurantsEntity = RestaurantsEntity(
            restaurants = restaurants
        )
        jsonValue = moshi.adapter(RestaurantsEntity::class.java)
            .toJson(restaurantsEntity)
        every { fileReader.getTextFromAssetFile(any()) }.returns(
            jsonValue
        )
        restaurantsLocalClient =
            RestaurantsLocalClient(moshi, fileReader)
    }

    @Test
    fun `given getRestaurants when called from repo then getRestaurants get called from client`() =
        runBlockingTest {
            restaurantsLocalClient.getRestaurants()
            coVerify {
                fileReader.getTextFromAssetFile(any())
            }
        }
}
