package io.aliabozid.justeat.restaurants.domain.repo

import io.aliabozid.justeat.sort.SelectedSort
import kotlinx.coroutines.flow.Flow

interface SelectedSortRepository {

    suspend fun saveSelectedSort(selectedSort: SelectedSort)

    suspend fun getSelectedSort(): Flow<SelectedSort>
}
