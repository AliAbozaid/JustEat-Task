package io.aliabozid.justeat.restaurants.domain

import io.aliabozid.justeat.restaurants.domain.repo.SelectedSortRepository
import io.aliabozid.justeat.sort.SelectedSort
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class SortSelectionUseCaseTest {
    private val selectedSortRepository: SelectedSortRepository =
        mockk(relaxed = true)
    lateinit var sortSelectionUseCase: SortSelectionUseCase

    @Before
    fun setUp() {
        sortSelectionUseCase = SortSelectionUseCase(selectedSortRepository)
    }

    @Test
    fun `given getSelectedSort when called from useCase then it get called from repo`() =
        runBlockingTest {
            sortSelectionUseCase.getSelectedSort()
            coVerify {
                selectedSortRepository.getSelectedSort()
            }
        }

    @Test
    fun `given setSelectedSort when called from useCase then it get called from repo`() =
        runBlockingTest {
            sortSelectionUseCase.setSelectedSort(SelectedSort.BEST_MATCH)
            coVerify {
                selectedSortRepository.saveSelectedSort(any())
            }
        }
}
