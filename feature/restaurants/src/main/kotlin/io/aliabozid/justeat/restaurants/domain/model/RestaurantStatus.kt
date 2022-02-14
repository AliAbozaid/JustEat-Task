package io.aliabozid.justeat.restaurants.domain.model

enum class RestaurantStatus constructor(private val key: String) {
    OPEN(RestaurantStatus.OPEN_KEY),
    ORDER_AHEAD(RestaurantStatus.ORDER_AHEAD_KEY),
    CLOSED(RestaurantStatus.CLOSED_KEY);

    companion object {
        private const val OPEN_KEY = "open"
        private const val ORDER_AHEAD_KEY = "order ahead"
        private const val CLOSED_KEY = "closed"

        fun getStatus(status: String?): RestaurantStatus =
            values().firstOrNull { it.key == status } ?: CLOSED
    }
}
