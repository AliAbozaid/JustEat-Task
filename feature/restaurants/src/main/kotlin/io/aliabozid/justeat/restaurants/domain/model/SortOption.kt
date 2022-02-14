package io.aliabozid.justeat.restaurants.domain.model

data class SortOption(
    val bestMatch: Double?,
    val newest: Double?,
    val ratingAverage: Double?,
    val distance: Double?,
    val popularity: Double?,
    val averageProductPrice: Double?,
    val deliveryCosts: Double?,
    val minCost: Double?
) {

    fun getDistance(): String =
        if (distance ?: 0.0 >= KM_VALUE) {
            String.format("%.1f km", distance?.div(KM_VALUE))
        } else {
            String.format("%f m", distance)
        }

    fun getCost() =
        String.format("%s ₹", minCost?.toInt().toString())

    companion object {
        private const val KM_VALUE: Double = 1000.0
    }
}
