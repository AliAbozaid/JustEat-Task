package io.aliabozid.justeat.restaurants.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.aliabozid.justeat.assets.utils.ResourceUi
import io.aliabozid.justeat.assets.utils.addDividerDecorator
import io.aliabozid.justeat.assets.utils.launchAndRepeatOnStart
import io.aliabozid.justeat.assets.utils.showErrorSnackBar
import io.aliabozid.justeat.assets.utils.viewBinding
import io.aliabozid.justeat.restaurants.R
import io.aliabozid.justeat.restaurants.databinding.FragmentRestaurantBinding
import io.aliabozid.justeat.sort.SelectedSort
import io.aliabozid.justeat.sort.SortListFragment
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantFragment : Fragment(R.layout.fragment_restaurant) {

    private val binding by viewBinding(FragmentRestaurantBinding::bind)
    private val viewModel by viewModel<RestaurantViewModel>()
    private val restaurantAdapter by lazy {
        RestaurantAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAppbar()
        setupRestaurant()
        bindObservers()
        setupSortOption()
        setupSearch()
    }

    private fun setupAppbar() {
        binding.toolbar.title = getString(R.string.app_name)
    }

    private fun setupRestaurant() {
        binding.restaurantRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = restaurantAdapter
            addDividerDecorator(
                startMarginId = R.dimen.space_16,
                endMarginId = R.dimen.space_16
            )
        }
    }

    private fun bindObservers() {
        launchAndRepeatOnStart {
            viewModel.restaurantStateFlow.collect { resource ->
                when (resource) {
                    ResourceUi.Loading -> {
                        binding.restaurantRecyclerView.isVisible = false
                        binding.searchProgress.isVisible = true
                    }
                    is ResourceUi.Failure -> {
                        binding.searchProgress.isVisible = false
                        requireContext().showErrorSnackBar(
                            container = binding.container,
                            message = resource.error.message.orEmpty()
                        )
                    }
                    is ResourceUi.Success -> {
                        binding.restaurantRecyclerView.isVisible = true
                        binding.searchProgress.isVisible = false
                        restaurantAdapter.submitList(resource.data) {
                            binding.restaurantRecyclerView.scrollToPosition(0)
                        }
                    }
                }
            }
        }
    }

    private fun setupSearch() {
        binding.search.editText?.setHint(R.string.search_text_hint)
        binding.search.editText?.doAfterTextChanged {
            viewModel.searchRestaurant(it?.toString() ?: "")
        }
    }

    private fun setupSortOption() {
        viewModel.selectedSortLiveData.observe(viewLifecycleOwner) {
            binding.sortType.setText(it.titleResKey)
            viewModel.selectedSortItem = it
        }
        binding.sort.setOnClickListener {
            findNavController().navigate(R.id.sort_list_navigation)
        }
        setFragmentResultListener(SortListFragment.SELECTED_SORT_REQUEST_KEY) { _, bundle ->
            val selectedSort = bundle.getParcelable<SelectedSort>(
                SortListFragment.SELECTED_SORT_RESULT
            )
            selectedSort?.let {
                viewModel.setSelectedSort(it)
            }
        }
    }
}
