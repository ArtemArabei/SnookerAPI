package com.example.snookerapi.presentation.ui.playerProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snookerapi.domain.usecase.GetPlayerProfileUseCase
import com.example.snookerapi.presentation.model.Lce
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class PlayerProfileViewModel(
    private val getPlayerProfileUseCase: GetPlayerProfileUseCase,
    private val id: Int,
) : ViewModel() {

    val snookerPlayerFlow = flow {
        val lce = runCatching {
            getPlayerProfileUseCase.invoke(id)
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
