package io.aliabozid.justeat.restaurants.data

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String, val cause: String) : Resource<T>()
}
