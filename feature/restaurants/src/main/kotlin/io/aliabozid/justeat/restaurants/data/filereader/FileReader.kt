package io.aliabozid.justeat.restaurants.data.filereader

interface FileReader {
    fun getTextFromAssetFile(fileName: String): String
}
