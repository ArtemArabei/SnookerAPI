package com.example.snookerapi.presentation.ui.eventProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.snookerapi.R
import com.example.snookerapi.databinding.FragmentEventProfileBinding
import com.example.snookerapi.presentation.model.Lce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class EventProfileFragment : Fragment() {

    private var _binding: FragmentEventProfileBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args by navArgs<EventProfileFragmentArgs>()

    private val viewModel by inject<EventProfileViewModel> { parametersOf(args.id) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentEventProfileBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel
            .snookerEventFlow
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { lce ->
                when (lce) {
                    Lce.Loading -> {
                        binding.loadingProgress.isVisible = true
                    }
                    is Lce.Content -> {
                        val data = lce.data[0]
                        with(binding) {
                            loadingProgress.isVisible = false
                            scrollView.isVisible = true
                            name.text = requireContext().getString(R.string.event_name,
                                data.name)
                            country.text = requireContext().getString(R.string.event_country,
                                data.country)
                            city.text = requireContext().getString(R.string.event_city,
                                data.city)
                            venue.text = requireContext().getString(R.string.event_venue,
                                data.venue)
                            dateOfStart.text =
                                requireContext().getString(R.string.date_of_start,
                                    data.dateOfStart)
                            dateOfEnd.text =
                                requireContext().getString(R.string.date_of_end,
                                    data.dateOfEnd)
                            showDefendingChampion.setOnClickListener {
                                val controller = findNavController()
                                when (val defendingChampion = data.defendingChampion) {
                                    0 -> Toast.makeText(requireContext(),
                                        getString(R.string.no_defending_champion),
                                        Toast.LENGTH_SHORT).show()
                                    else -> viewModel.onButtonClicked(controller, defendingChampion)
                                }
                            }
                        }
                    }
                    is Lce.Error -> {
                        Toast.makeText(requireContext(),
                            lce.throwable.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.toolbar.setupWithNavController(findNavController())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}