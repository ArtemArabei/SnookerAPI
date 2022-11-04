package com.example.snookerapi.presentation.ui.allEvents

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.snookerapi.databinding.ItemEventBinding
import com.example.snookerapi.domain.model.Event

class EventViewHolder(
    private val binding: ItemEventBinding,
    private val onEventClicked: (Event) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: Event) {
        with(binding) {
            eventInfo.text = "${item.name}:\n${item.dateOfStart} - ${item.dateOfEnd}"

            root.setOnClickListener {
                onEventClicked(item)
            }
        }
    }
}