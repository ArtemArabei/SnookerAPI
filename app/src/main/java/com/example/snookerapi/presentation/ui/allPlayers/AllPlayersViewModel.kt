package com.example.snookerapi.presentation.ui.allPlayers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snookerapi.presentation.model.Lce
import com.example.snookerapi.domain.model.Player
import com.example.snookerapi.domain.usecase.GetApiPlayersUseCase
import com.example.snookerapi.domain.usecase.GetDatabasePlayersUseCase
import com.example.snookerapi.domain.usecase.SaveDatabasePlayersUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class AllPlayersViewModel(
    private val getApiPlayersUseCase: GetApiPlayersUseCase,
    private val getDatabasePlayersUseCase: GetDatabasePlayersUseCase,
    private val saveDatabasePlayersUseCase: SaveDatabasePlayersUseCase,
    private val year: Int,
) : ViewModel() {

    private val queryFlow = MutableStateFlow("")

    private val refreshFlow = MutableSharedFlow<Unit>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val snookerDataFlow: Flow<Lce<List<Player>>> =
        queryFlow.combine(
            refreshFlow.map {
                runCatching {
                    getApiPlayersUseCase.invoke(year)
                }
            }) { query, result ->
            result.map { players ->
                players.filter {
                    it.firstName.contains(query, ignoreCase = true) ||
                            it.lastName.contains(query, ignoreCase = true)
                }
            }.fold(
                onSuccess = {
                    saveDatabasePlayersUseCase.invoke(it)
                    Lce.Content(it)
                },
                onFailure = { Lce.Error(it) }
            )
        }.onStart {
            val roomPlayers = getDatabasePlayersUseCase.invoke()
            val state = if (roomPlayers.isNotEmpty()) {
                Lce.Content(roomPlayers)
            } else {
                Lce.Loading
            }
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