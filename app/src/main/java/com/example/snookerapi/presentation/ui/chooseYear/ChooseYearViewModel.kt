package com.example.snookerapi.presentation.ui.chooseYear

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class ChooseYearViewModel() : ViewModel() {

    fun onButtonShowPlayersClicked(controller: NavController, yearToShow: Int) {
        controller.navigate(
            ChooseYearFragmentDirections.toAllPlayers(yearToShow)
        )
    }

    fun onButtonShowEventsClicked(controller: NavController, yearToShow: Int) {
        controller.navigate(
            ChooseYearFragmentDirections.toAllEvents(yearToShow)
        )
    }

    fun onButtonShowMapClicked(controller: NavController) {
        controller.navigate(
            ChooseYearFragmentDirections.toAllCountries()
        )
    }
}
