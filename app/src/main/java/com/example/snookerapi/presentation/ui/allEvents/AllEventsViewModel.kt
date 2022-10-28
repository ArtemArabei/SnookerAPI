package com.example.snookerapi.presentation.ui.allEvents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snookerapi.domain.model.Event
import com.example.snookerapi.domain.usecase.GetApiEventsUseCase
import com.example.snookerapi.presentation.model.Lce
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class AllEventsViewModel(
    private val getApiEventsUseCase: GetApiEventsUseCase,
    private val year: Int,
) : ViewModel() {

    private val queryFlow = MutableStateFlow("")

    private val refreshFlow = MutableSharedFlow<Unit>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val snookerDataFlow: Flow<Lce<List<Event>>> =
        queryFlow.combine(
            refreshFlow.map {
                runCatching {
                    getApiEventsUseCase.invoke(year)
                }
            }) { query, result ->
            result.map { events ->
                events.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            }.fold(
                onSuccess = {
                    Lce.Content(it)
                },
                onFailure = { Lce.Error(it) }
            )
        }.onStart {
            val state = Lce.Loading
            emit(state)
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            initialValue = Lce.Loading
        )

    init {
        onRefreshed()
    }

    fun onQueryChanged(query: String) {
        queryFlow.value = query
    }

    fun onRefreshed() {
        refreshFlow.tryEmit(Unit)
    }
}