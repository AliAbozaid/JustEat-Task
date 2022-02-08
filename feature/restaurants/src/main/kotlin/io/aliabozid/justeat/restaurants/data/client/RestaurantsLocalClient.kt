package io.aliabozid.justeat.restaurants.data.client

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import io.aliabozid.justeat.restaurants.BuildConfig
import io.aliabozid.justeat.restaurants.data.entity.RestaurantEntity
import io.aliabozid.justeat.restaurants.data.entity.RestaurantsEntity
import io.aliabozid.justeat.restaurants.data.filereader.FileReader
import java.io.FileNotFoundException
import java.io.IOException

class RestaurantsLocalClient constructor(
    private val moshi: Moshi,
    private val fileReader: FileReader
) {

    fun getRestaurants(): FileResponse<List<RestaurantEntity>> =
        try {
            val text =
                fileReader.getTextFromAssetFile(BuildConfig.RESTAURANTS_FILE)
            val restaurantsEntity =
                moshi.adapter(RestaurantsEntity::class.java).fromJson(text)
            restaurantsEntity?.restaurants?.let {
                FileResponse.FileText(restaurantsEntity.restaurants)
            } ?: kotlin.run {
                FileResponse.Error(
                    "there are no restaurants in the set",
                    RestaurantFileError.NULL_EXCEPTION.cause
                )
            }
        } catch (exc: FileNotFoundException) {
            exc.message?.let {
                FileResponse.Error(
                    it,
                    RestaurantFileError.FILE_NOT_FOUND.cause
                )
            } ?: run {
                FileResponse.Error(
                    "file not found",
                    RestaurantFileError.FILE_NOT_FOUND.cause
                )
            }
        } catch (exc: JsonDataException) {
            exc.message?.let {
                FileResponse.Error(
                    it,
                    RestaurantFileError.PARSE_EXCEPTION.cause
                )
            } ?: kotlin.run {
                FileResponse.Error(
                    "json parsing error",
                    RestaurantFileError.PARSE_EXCEPTION.cause
                )
            }
        } catch (exc: IOException) {
            exc.message?.let {
                FileResponse.Error(
                    it,
                    RestaurantFileError.IO_EXCEPTION.cause
                )
            } ?: kotlin.run {
                FileResponse.Error(
                    "json parsing error",
                    RestaurantFileError.IO_EXCEPTION.cause
                )
            }
        }
}
