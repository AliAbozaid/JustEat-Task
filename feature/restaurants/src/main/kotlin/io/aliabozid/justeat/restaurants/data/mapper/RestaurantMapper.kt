package io.aliabozid.justeat.restaurants.data.mapper

import io.aliabozid.justeat.restaurants.data.entity.RestaurantEntity
import io.aliabozid.justeat.restaurants.domain.model.Restaurant
import io.aliabozid.justeat.restaurants.domain.model.RestaurantStatus
import io.aliabozid.justeat.restaurants.domain.model.SortOption

fun RestaurantEntity.mapToRestaurant() =
    Restaurant(
        name = name,
        status = status?.let { RestaurantStatus.getStatus(it) },
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
