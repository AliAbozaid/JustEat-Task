package io.aliabozid.justeat.restaurants.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.aliabozid.justeat.assets.utils.layoutInflater
import io.aliabozid.justeat.restaurants.data.model.Restaurant
import io.aliabozid.justeat.restaurants.databinding.RowRestaurantBinding
import io.aliabozid.justeat.sort.SelectedSort

class RestaurantAdapter constructor(var selectedSort: SelectedSort) :
    ListAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder>(
        object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(
                oldItem: Restaurant,
                newItem: Restaurant
            ): Boolean =
                oldItem.name == newItem.name &&
                    oldItem.sortingValues?.getSortType(
                    selectedSort
                ) ==
                    newItem.sortingValues?.getSortType(selectedSort)

            override fun areContentsTheSame(
                oldItem: Restaurant,
                newItem: Restaurant
            ): Boolean =
                oldItem == newItem && oldItem.sortingValues?.getSortType(
                    selectedSort
                ) ==
                    newItem.sortingValues?.getSortType(selectedSort)
        }
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantAdapter.RestaurantViewHolder {
        return RestaurantViewHolder(
            RowRestaurantBinding.inflate(
                parent.layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: RestaurantAdapter.RestaurantViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class RestaurantViewHolder(
        private val binding: RowRestaurantBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.restaurantName.text = restaurant.name
            binding.status.text = restaurant.status?.name
            binding.distance.text =
                getDistance(restaurant.sortingValues?.distance)
            binding.cost.text = getCost(restaurant.sortingValues?.minCost)
            binding.rating.text =
                restaurant.sortingValues?.ratingAverage.toString()
        }

        private fun getDistance(distance: Double?): String =
            if (distance ?: 0.0 >= KM_VALUE) {
                String.format("%.1f km", distance?.div(KM_VALUE))
            } else {
                String.format("%f m", distance)
            }

        private fun getCost(cost: Double?) =
            String.format("%s ₹", cost?.toInt().toString())
    }

    companion object {
        private const val KM_VALUE: Double = 1000.0
    }
}
