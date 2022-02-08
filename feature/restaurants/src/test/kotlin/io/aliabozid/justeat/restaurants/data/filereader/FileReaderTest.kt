package io.aliabozid.justeat.restaurants.data.filereader

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import io.aliabozid.justeat.restaurants.data.client.FileResponse
import io.aliabozid.justeat.restaurants.data.client.RestaurantsLocalClient
import io.aliabozid.justeat.restaurants.data.entity.RestaurantEntity
import io.aliabozid.justeat.restaurants.data.entity.RestaurantsEntity
import io.aliabozid.justeat.restaurants.data.entity.SortingEntity
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class FileReaderTest {

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

	private val moshi = Moshi.Builder().build()
	private val fileReader = mockk<FileReader>()

	@Before
	fun setUp() {
		val restaurants = mutableListOf<RestaurantEntity>()
		restaurants.add(restaurantEntity)
		restaurantsEntity = RestaurantsEntity(
			restaurants = restaurants
		)
		jsonValue = moshi.adapter(RestaurantsEntity::class.java)
			.toJson(restaurantsEntity)
	}

	@Test
	fun `given getTextFromAssetFile called when load restaurant file then return success`() =
		runBlocking {
			every { fileReader.getTextFromAssetFile("restaurants.json") }.returns(
				jsonValue
			)
			val client = RestaurantsLocalClient(moshi, fileReader)
			MatcherAssert.assertThat(
				"",
				client.getRestaurants() is FileResponse.FileText
			)
			Assert.assertEquals(
				(client.getRestaurants() as FileResponse.FileText).data.size,
				1
			)
		}

	@Test
	fun `given getTextFromAssetFile called when empty file then return error`() =
		runBlocking {
			try {
				every { fileReader.getTextFromAssetFile("") }.throws(
					FileNotFoundException("")
				)

				val client = RestaurantsLocalClient(moshi, fileReader)
				MatcherAssert.assertThat(
					"",
					client.getRestaurants() is FileResponse.Error
				)
				Assert.assertEquals(
					(client.getRestaurants() as FileResponse.Error).cause,
					"file not found"
				)
			} catch (exc: Exception) {
			}
		}

	@Test
	fun `given getTextFromAssetFile called when wrong parse then return error`() =
		runBlocking {
			every { fileReader.getTextFromAssetFile("restaurants.json") }.throws(
				JsonDataException()
			)
			val client = RestaurantsLocalClient(moshi, fileReader)
			MatcherAssert.assertThat(
				"",
				client.getRestaurants() is FileResponse.Error
			)
			Assert.assertEquals(
				(client.getRestaurants() as FileResponse.Error).cause,
				"JsonDataException"
			)
		}
}
