package com.example.snookerapi.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snookerapi.model.Lce
import com.example.snookerapi.servicelocator.ApiDataSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class PlayerProfileViewModel(
    snookerApi: ApiDataSource,
    args: PlayerProfileFragmentArgs,
) : ViewModel() {

    val snookerPlayerFlow = flow {
        val lce = runCatching {
            snookerApi.getPlayerProfile(args.id)
        }.fold(
            onSuccess = { Lce.Content(it) },
            onFailure = { Lce.Error(it) }
        )
        emit(lce)
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = Lce.Loading
    )

}
