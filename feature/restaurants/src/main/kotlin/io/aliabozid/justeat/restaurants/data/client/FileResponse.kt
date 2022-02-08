package io.aliabozid.justeat.restaurants.data.client

sealed class FileResponse<T>() {
    class FileText<T>(val data: T) : FileResponse<T>()
    class Error<T>(val message: String, val cause: String) : FileResponse<T>()
}
