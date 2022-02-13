package io.aliabozid.justeat.sort

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.aliabozid.justeat.assets.utils.layoutInflater
import io.aliabozid.justeat.sort.databinding.RowSortItemBinding

class SortAdapter constructor(
    private val onSelectedSort: (SelectedSort) -> Unit
) :
    ListAdapter<SelectedSort, SortAdapter.SortItemViewHolder>(
        object : DiffUtil.ItemCallback<SelectedSort>() {
            override fun areItemsTheSame(
                oldItem: SelectedSort,
                newItem: SelectedSort
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: SelectedSort,
                newItem: SelectedSort
            ): Boolean = oldItem == newItem
        }
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SortAdapter.SortItemViewHolder {
        return SortItemViewHolder(
            RowSortItemBinding.inflate(
                parent.layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SortAdapter.SortItemViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onSelectedSort.invoke(getItem(position))
        }
    }

    inner class SortItemViewHolder(
        private val binding: RowSortItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: SelectedSort) {
            binding.type.setText(restaurant.titleResKey)
        }
    }
}
