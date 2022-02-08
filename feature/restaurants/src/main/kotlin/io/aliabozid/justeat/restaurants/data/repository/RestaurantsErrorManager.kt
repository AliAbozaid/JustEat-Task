package io.aliabozid.justeat.restaurants.data.repository

import android.content.Context
import io.aliabozid.justeat.restaurants.R
import io.aliabozid.justeat.restaurants.data.client.RestaurantFileError

class RestaurantsErrorManager constructor(
    private val context: Context
) {
    fun getErrorMessage(key: String): String {
        if (errorsMap.containsKey(key))
            if (errorsMap[key] != null)
                return errorsMap[key]!!
        return context.getString(R.string.default_error)
    }

    private val errorsMap: Map<String, String>
        get() = mapOf(
            Pair(
                RestaurantFileError.FILE_NOT_FOUND.cause,
                context.getString(R.string.no_file)
            ),
            Pair(
                RestaurantFileError.PARSE_EXCEPTION.cause,
                context.getString(R.string.parsing_error)
            ),
            Pair(
                RestaurantFileError.NULL_EXCEPTION.cause,
                context.getString(R.string.default_error)
            ),
            Pair(
                RestaurantFileError.EMPTY_RESTAURANT_LIST.cause,
                context.getString(R.string.no_restaurants_found)
            ),

        )
}
