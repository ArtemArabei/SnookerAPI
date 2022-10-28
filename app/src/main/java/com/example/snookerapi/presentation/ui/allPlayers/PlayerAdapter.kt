package com.example.snookerapi.presentation.ui.allPlayers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.snookerapi.databinding.ItemPlayerBinding
import com.example.snookerapi.domain.model.Player

class PlayerAdapter(
    context: Context,
    private val onPlayerClicked: (Player) -> Unit,
) : ListAdapter<Player, PlayerViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            binding = ItemPlayerBinding.inflate(layoutInflater, parent, false),
            onPlayerClicked = onPlayerClicked
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = getItem(position)
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