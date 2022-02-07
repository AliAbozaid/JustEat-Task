package io.aliabozid.justeat.restaurants.data.filereader

import android.content.Context
import java.io.FileNotFoundException


class FileReaderImpl constructor(private val context: Context) : FileReader {
	override fun getTextFromAssetFile(fileName: String): String {
		try {

			return context.assets.open(fileName).bufferedReader()
				.use { it.readText() }
		} catch (exc: Exception) {
			throw (
				FileNotFoundException(
					"No file named for the name " +
						"$fileName make sure the file is in the assets folder"
				)
				)
		}
	}
}