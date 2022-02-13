package io.aliabozid.justeat.restaurants.data.client

enum class RestaurantFileError constructor(val cause: String) {
    FILE_NOT_FOUND(RestaurantFileError.FILE_NOT_FOUND_CAUSE),
    IO_EXCEPTION(RestaurantFileError.IO_EXCEPTION_CAUSE),
    PARSE_EXCEPTION(RestaurantFileError.PARSE_EXCEPTION_CAUSE),
    NULL_EXCEPTION(RestaurantFileError.NULL_EXCEPTION_CAUSE),
    EMPTY_RESTAURANT_LIST(RestaurantFileError.EMPTY_RESTAURANT_LIST_CAUSE);

    companion object {
        private const val FILE_NOT_FOUND_CAUSE = "FileNotFoundException"
        private const val IO_EXCEPTION_CAUSE = "IOException"
        private const val PARSE_EXCEPTION_CAUSE = "JsonDataException"
        private const val NULL_EXCEPTION_CAUSE = "NullPointerException"
        private const val EMPTY_RESTAURANT_LIST_CAUSE = "EmptyRestaurant"
    }
}
