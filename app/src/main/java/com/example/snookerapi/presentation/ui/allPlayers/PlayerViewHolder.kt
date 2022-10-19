package com.example.snookerapi.presentation.ui.allPlayers

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.snookerapi.domain.model.Player
import com.example.snookerapi.R
import com.example.snookerapi.databinding.ItemPlayerBinding

class PlayerViewHolder(
    private val binding: ItemPlayerBinding,
    private val onPlayerClicked: (Player) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: Player) {
        with(binding) {
            if (item.photoUrl != "") {
                playerPhoto.load(item.photoUrl)
            } else {
                playerPhoto.load(R.drawable.no_photo)
            }

            playerFullName.text = "${item.firstName} ${item.lastName}"

            root.setOnClickListener {
                onPlayerClicked(item)
            }
        }
    }
}