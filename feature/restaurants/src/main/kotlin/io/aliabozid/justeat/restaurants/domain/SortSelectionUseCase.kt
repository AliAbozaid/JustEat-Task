package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.domain.repo.SelectedSortRepository
import io.aliabozid.justeat.sort.SelectedSort

class SortSelectionUseCase constructor(
    private val selectedSortRepository: SelectedSortRepository
) {

    suspend fun setSelectedSort(selectedSort: SelectedSort) {
        selectedSortRepository.saveSelectedSort(selectedSort)
    }

    suspend fun getSelectedSort() = selectedSortRepository.getSelectedSort()
}
