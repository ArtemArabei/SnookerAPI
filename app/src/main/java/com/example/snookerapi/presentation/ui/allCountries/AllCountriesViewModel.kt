package com.example.snookerapi.presentation.ui.allCountries

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snookerapi.data.service.LocationService
import com.example.snookerapi.domain.model.Country
import com.example.snookerapi.domain.usecase.GetApiCountriesUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class AllCountriesViewModel(
    private val locationService: LocationService,
    private val getApiCountriesUseCase: GetApiCountriesUseCase,
) : ViewModel() {

    private val markerFlow = MutableSharedFlow<Pair<Double, Double>>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val locationFlow: Flow<Location> by locationService::locationFlow

    val startLocationFlow: Flow<Location> = flow {
        locationService.getLocation()?.let { emit(it) }
    }.shareIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        replay = 0
    )

    val countriesFlow: Flow<List<Country>> = flow {
        emit(getApiCountriesUseCase().getOrThrow())
    }.retry(3) {
        delay(1000)
        true
    }.catch {
        emit(emptyList())
    }.shareIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        replay = 1
    )

    val selectedCountryFlow =
        countriesFlow.combine(markerFlow) { countries, (latitude, longitude) ->
            countries.first { it.latitude == latitude && it.longitude == longitude }
        }

    fun onMarkerClicked(latitude: Double, longitude: Double) {
        markerFlow.tryEmit(latitude to longitude)
    }
}