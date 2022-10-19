package com.example.snookerapi.presentation.ui.eventProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.snookerapi.domain.usecase.GetEventProfileUseCase
import com.example.snookerapi.presentation.model.Lce
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class EventProfileViewModel(
    private val getEventProfileUseCase: GetEventProfileUseCase,
    private val id: Int,
) : ViewModel() {

    val snookerEventFlow = flow {
        val lce = runCatching {
            getEventProfileUseCase.invoke(id)
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

    fun onButtonClicked(controller: NavController, defendingChampion: Int) {
        controller.navigate(
            EventProfileFragmentDirections.toPlayerProfile(defendingChampion)
        )
    }

}