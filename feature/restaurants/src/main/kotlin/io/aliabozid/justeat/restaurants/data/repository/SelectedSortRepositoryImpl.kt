package io.aliabozid.justeat.restaurants.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import io.aliabozid.justeat.restaurants.domain.repo.SelectedSortRepository
import io.aliabozid.justeat.sort.SelectedSort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DataStore_NAME = "selected_sort"

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DataStore_NAME)

class SelectedSortRepositoryImpl(
    private val context: Context
) : SelectedSortRepository {

    override suspend fun saveSelectedSort(selectedSort: SelectedSort) {
        context.datastore.edit { preference ->
            preference[SELECTED_SORT] = selectedSort.ordinal
        }
    }

    override suspend fun getSelectedSort(): Flow<SelectedSort> {
        return context.datastore.data.map { preference ->
            SelectedSort.values()[preference[SELECTED_SORT] ?: 0]
        }
    }

    companion object {
        val SELECTED_SORT = intPreferencesKey("SELECTED_SORT")
    }
}
