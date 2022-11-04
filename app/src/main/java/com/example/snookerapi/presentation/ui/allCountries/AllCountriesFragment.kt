package com.example.snookerapi.presentation.ui.allCountries

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.snookerapi.R
import com.example.snookerapi.databinding.FragmentAllCountriesBinding
import com.example.snookerapi.presentation.extensions.checkPermission
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCountriesFragment : Fragment() {

    private var _binding: FragmentAllCountriesBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by viewModel<AllCountriesViewModel>()

    private var googleMap: GoogleMap? = null
    private var locationListener: LocationSource.OnLocationChangedListener? = null

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isEnabled ->
        setLocationEnabled(isEnabled)
        if (isEnabled) {
            observeLocationChanges()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentAllCountriesBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)

        initGoogleMap { map ->
            map.setOnMapClickListener {
                if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

            viewModel
                .countriesFlow
                .onEach { countries ->
                    countries.forEach {
                        map.addMarker(
                            MarkerOptions().position(LatLng(it.latitude, it.longitude))
                        )
                    }
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }

        viewModel
            .selectedCountryFlow
            .onEach {
                with(binding.bottomSheetContent) {
                    flag.load(it.flagUrl)
                    emblem.load(it.emblemUrl)
                    countryName.text = requireContext().getString(R.string.country_name,
                        it.name)
                    capitalName.text = requireContext().getString(R.string.capital_name,
                        it.capital)
                    countryArea.text = requireContext().getString(R.string.country_area,
                        it.area.toString())
                    countryPopulation.text = requireContext().getString(R.string.country_population,
                        it.population.toString())
                }
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.map.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        binding.map.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.map.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.map.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.map.onDestroy()
        googleMap = null
        _binding = null
    }

    private fun initGoogleMap(action: (GoogleMap) -> Unit) {
        binding.map.getMapAsync { map ->
            googleMap = map.apply {

                uiSettings.isCompassEnabled = true
                uiSettings.isZoomControlsEnabled = true

                setLocationSource(object : LocationSource {
                    override fun activate(listener: LocationSource.OnLocationChangedListener) {
                        locationListener = listener
                    }

                    override fun deactivate() {
                        locationListener = null
                    }
                })

                setOnMarkerClickListener {
                    viewModel.onMarkerClicked(it.position.latitude, it.position.longitude)
                    true
                }
            }

            val hasLocationPermission =
                requireContext().checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            setLocationEnabled(hasLocationPermission)

            if (requireContext().checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                observeLocationChanges()
            } else {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }

            action(map)
        }
    }

    private fun observeLocationChanges() {
        viewModel
            .startLocationFlow
            .onEach(::moveCameraToLocation)
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel
            .locationFlow
            .onEach {
                locationListener?.onLocationChanged(it)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    @SuppressLint("MissingPermission")
    private fun setLocationEnabled(enabled: Boolean) {
        googleMap?.isMyLocationEnabled = enabled
        googleMap?.uiSettings?.isMyLocationButtonEnabled = enabled
    }

    private fun moveCameraToLocation(location: Location) {
        val current = LatLng(location.latitude, location.longitude)
        googleMap?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(current, DEFAULT_CAMERA_ZOOM)
        )
    }

    companion object {
        private const val DEFAULT_CAMERA_ZOOM = 17f
    }
}
