package io.aliabozid.justeat.restaurants.data.helper

import android.content.SharedPreferences
import io.aliabozid.justeat.sort.SelectedSort

class PreferenceHelper constructor(
	private val pref: SharedPreferences
) {

	private fun edit(key: String, value: Any?) {
		val editMe = pref.edit()
		when (value) {
			is String? -> editMe.putString(key, value)
			is Int -> editMe.putInt(key, value)
			is Boolean -> editMe.putBoolean(key, value)
			is Long -> editMe.putLong(key, value)
			is Float -> editMe.putFloat(key, value)
			else -> error("Only primitive types can be stored in SharedPreferences")
		}
		editMe.apply()
	}

	fun clear() {
		pref.edit().clear().apply()
	}

	var selectedSort: SelectedSort
		get() {
			return SelectedSort.values()[pref.getInt(KEY_SELECTED_SORT, 0)]
		}
		set(value) {
			edit(KEY_SELECTED_SORT, value.ordinal)
		}


	companion object {
		private const val KEY_SELECTED_SORT = "selected_sort"
	}
}
