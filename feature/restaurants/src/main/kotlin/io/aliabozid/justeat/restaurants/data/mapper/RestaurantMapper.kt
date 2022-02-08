package io.aliabozid.justeat.restaurants.data.mapper

import io.aliabozid.justeat.restaurants.data.entity.RestaurantEntity
import io.aliabozid.justeat.restaurants.data.model.Restaurant
import io.aliabozid.justeat.restaurants.data.model.RestaurantStatus
import io.aliabozid.justeat.restaurants.data.model.SortOption

fun RestaurantEntity.mapToRestaurant() =
    Restaurant(
        name = name,
        status = status?.let { RestaurantStatus.valueOf(it.uppercase()) },
        sortingValues = SortOption(
            bestMatch = sortingValues?.bestMatch,
            newest = sortingValues?.newest,
            ratingAverage = sortingValues?.ratingAverage,
            distance = sortingValues?.distance,
            popularity = sortingValues?.popularity,
            averageProductPrice = sortingValues?.averageProductPrice,
            deliveryCosts = sortingValues?.deliveryCosts,
            minCost = sortingValues?.minCost
        )
    )
