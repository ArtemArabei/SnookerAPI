package com.example.snookerapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.snookerapi.databinding.ItemPlayerBinding
import com.example.snookerapi.model.Player

class PlayerAdapter(
    context: Context,
    private val onPlayerClicked: (Player) -> Unit,
) : ListAdapter<Player, RecyclerView.ViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlayerViewHolder(
            binding = ItemPlayerBinding.inflate(layoutInflater, parent, false),
            onPlayerClicked = onPlayerClicked
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        checkNotNull(holder as PlayerViewHolder) { "Incorrect viewHolder $item" }
        holder.bind(item)
    }

    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Player>() {

            override fun areItemsTheSame(
                oldItem: Player,
                newItem: Player,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Player,
                newItem: Player,
            ): Boolean {
                return (oldItem.firstName == newItem.firstName) && (oldItem.lastName == newItem.lastName)
            }
        }
    }
}