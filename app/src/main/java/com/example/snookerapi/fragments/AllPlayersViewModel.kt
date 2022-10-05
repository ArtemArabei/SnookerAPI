package com.example.snookerapi.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snookerapi.database.PlayerDao
import com.example.snookerapi.model.Lce
import com.example.snookerapi.model.Player
import com.example.snookerapi.servicelocator.ApiDataSource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class AllPlayersViewModel(
    private val snookerApi: ApiDataSource,
    private val playerDao: PlayerDao,
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
                    snookerApi.getPlayers()
                }.onSuccess { Lce.Content(it) }
                    .onFailure { Lce.Error(it) }
            }) { query, result ->
            result.map { players ->
                players.filter {
                    it.firstName.contains(query, ignoreCase = true) ||
                            it.lastName.contains(query, ignoreCase = true)
                }
            }.fold(
                onSuccess = {
                    playerDao.insertAll(it)
                    Lce.Content(it)
                },
                onFailure = { Lce.Error(it) }
            )
        }.onStart {
            val roomPlayers = playerDao.getAll()
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