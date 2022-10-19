package com.example.snookerapi.presentation.ui.allEvents

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.snookerapi.databinding.ItemEventBinding
import com.example.snookerapi.domain.model.Event

class EventAdapter(
    context: Context,
    private val onEventClicked: (Event) -> Unit,
) : ListAdapter<Event, EventViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            binding = ItemEventBinding.inflate(layoutInflater, parent, false),
            onEventClicked = onEventClicked
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Event>() {

            override fun areItemsTheSame(
                oldItem: Event,
                newItem: Event,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Event,
                newItem: Event,
            ): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}