package com.example.snookerapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.snookerapi.model.PagingData
import com.example.snookerapi.model.Player
import com.example.snookerapi.databinding.ItemLoadingBinding
import com.example.snookerapi.databinding.ItemPlayerBinding

class PlayerAdapter(
    context: Context,
    private val onPlayerClicked: (Player) -> Unit,
) : ListAdapter<PagingData<Player>, RecyclerView.ViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PagingData.Item -> TYPE_PLAYER
            PagingData.Loading -> TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PLAYER -> {
                PlayerViewHolder(
                    binding = ItemPlayerBinding.inflate(layoutInflater, parent, false),
                    onPlayerClicked = onPlayerClicked
                )
            }
            TYPE_LOADING -> {
                LoadingViewHolder(
                    binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> error("Unsupported viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is PagingData.Item -> {
                checkNotNull(holder as PlayerViewHolder) { "Incorrect viewHolder $item" }
                holder.bind(item.data)
            }
            PagingData.Loading -> {
                // no operation
            }
        }
    }

    companion object {

        private const val TYPE_PLAYER = 0
        private const val TYPE_LOADING = 1

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<PagingData<Player>>() {

            override fun areItemsTheSame(
                oldItem: PagingData<Player>,
                newItem: PagingData<Player>,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PagingData<Player>,
                newItem: PagingData<Player>,
            ): Boolean {
                val oldPlayer = oldItem as? PagingData.Item
                val newPlayer = newItem as? PagingData.Item
                return oldPlayer?.data == newPlayer?.data
            }

        }
    }
}