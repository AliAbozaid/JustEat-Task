package io.aliabozid.justeat.restaurants.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.aliabozid.justeat.assets.utils.layoutInflater
import io.aliabozid.justeat.restaurants.databinding.RowRestaurantBinding
import io.aliabozid.justeat.restaurants.domain.model.Restaurant

class RestaurantAdapter :
    ListAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder>(
        object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(
                oldItem: Restaurant,
                newItem: Restaurant
            ): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: Restaurant,
                newItem: Restaurant
            ): Boolean =
                oldItem == newItem
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
            binding.distance.text = restaurant.sortingValues?.getDistance()
            binding.cost.text = restaurant.sortingValues?.getCost()
            binding.rating.text =
                restaurant.sortingValues?.ratingAverage.toString()
        }
    }
}
