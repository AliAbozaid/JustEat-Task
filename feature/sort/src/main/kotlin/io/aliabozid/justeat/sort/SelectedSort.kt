package io.aliabozid.justeat.sort

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
enum class SelectedSort constructor(
    @StringRes val titleResKey: Int
) : Parcelable {
    BEST_MATCH(R.string.best_match),
    NEWEST(R.string.newest),
    RATING_AVERAGE(R.string.rating_average),
    DISTANCE(R.string.distance),
    POPULARITY(R.string.popularity),
    AVERAGE_PRODUCT_PRICE(R.string.average_price),
    DELIVERY_COSTS(R.string.delivery_cost),
    MIN_COST(R.string.min_cost);
}
