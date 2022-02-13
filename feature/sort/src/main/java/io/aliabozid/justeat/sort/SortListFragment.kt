package io.aliabozid.justeat.sort

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.aliabozid.justeat.sort.databinding.FragmentSortListBinding

class SortListFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSortListBinding? = null
    private val binding: FragmentSortListBinding
        get() = _binding!!

    private val sortAdapter by lazy {
        SortAdapter(
            onSelectedSort = ::setSelectedSort
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog =
            super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val parentLayout =
                bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet)
            parentLayout?.let { view ->
                val bottomSheetBehaviour = BottomSheetBehavior.from(view)
                val layoutParams = view.layoutParams
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
                view.layoutParams = layoutParams
                bottomSheetBehaviour.state =
                    BottomSheetBehavior.STATE_HALF_EXPANDED
                bottomSheetBehaviour.skipCollapsed = true
            }
        }
        return bottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSortListBinding.inflate(inflater)
        setupRecycler()
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupRecycler() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = sortAdapter
        }
        sortAdapter.submitList(SelectedSort.values().toList())
    }

    private fun setSelectedSort(SelectedSort: SelectedSort) {
        setFragmentResult(
            SELECTED_SORT_REQUEST_KEY,
            bundleOf(
                SELECTED_SORT_RESULT to SelectedSort
            )
        )
        dismiss()
    }

    companion object {
        const val SELECTED_SORT_REQUEST_KEY = "selected_sort_request_key"
        const val SELECTED_SORT_RESULT = "selected_sort_result"
    }
}
