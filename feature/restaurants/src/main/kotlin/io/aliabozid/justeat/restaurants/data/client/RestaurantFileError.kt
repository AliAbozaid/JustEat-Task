package io.aliabozid.justeat.restaurants.data.client

enum class RestaurantFileError constructor(val cause: String) {
    FILE_NOT_FOUND(RestaurantFileError.FILE_NOT_FOUND),
    IO_EXCEPTION(RestaurantFileError.IO_EXCEPTION),
    PARSE_EXCEPTION(RestaurantFileError.PARSE_EXCEPTION),
    NULL_EXCEPTION(RestaurantFileError.NULL_EXCEPTION),
    EMPTY_RESTAURANT_LIST(RestaurantFileError.EMPTY_RESTAURANT_LIST);

    companion object {
        private const val FILE_NOT_FOUND = "FileNotFoundException"
        private const val IO_EXCEPTION = "IOException"
        private const val PARSE_EXCEPTION = "JsonDataException"
        private const val NULL_EXCEPTION = "NullPointerException"
        private const val EMPTY_RESTAURANT_LIST = "EmptyRestaurant"
    }
}
