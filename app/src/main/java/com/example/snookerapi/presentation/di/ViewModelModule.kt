package com.example.snookerapi.presentation.di

import com.example.snookerapi.presentation.ui.allCountries.AllCountriesViewModel
import com.example.snookerapi.presentation.ui.allEvents.AllEventsViewModel
import com.example.snookerapi.presentation.ui.allPlayers.AllPlayersViewModel
import com.example.snookerapi.presentation.ui.chooseYear.ChooseYearViewModel
import com.example.snookerapi.presentation.ui.eventProfile.EventProfileViewModel
import com.example.snookerapi.presentation.ui.playerProfile.*
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AllPlayersViewModel)
    viewModelOf(::PlayerProfileViewModel)
    viewModelOf(::ChooseYearViewModel)
    viewModelOf(::AllEventsViewModel)
    viewModelOf(::EventProfileViewModel)
    viewModelOf(::AllCountriesViewModel)
}