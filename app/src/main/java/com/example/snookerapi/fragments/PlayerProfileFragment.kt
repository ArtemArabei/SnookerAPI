package com.example.snookerapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.example.snookerapi.R
import com.example.snookerapi.databinding.FragmentPlayerProfileBinding
import com.example.snookerapi.model.Lce
import com.example.snookerapi.servicelocator.ServiceLocator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PlayerProfileFragment : Fragment() {

    private var _binding: FragmentPlayerProfileBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args by navArgs<PlayerProfileFragmentArgs>()

    private val viewModel: PlayerProfileViewModel by viewModels(
        factoryProducer = {
            viewModelFactory {
                initializer {
                    PlayerProfileViewModel(snookerApi = ServiceLocator.provideApiDataSource(), args)
                }
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentPlayerProfileBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel
            .snookerPlayerFlow
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
                            if (data.photoUrl != "") {
                                playerPhoto.load(data.photoUrl)
                            } else {
                                playerPhoto.load(R.drawable.no_photo)
                            }
                            firstName.text = requireContext().getString(R.string.first_name,
                                data.firstName)
                            lastName.text = requireContext().getString(R.string.last_name,
                                data.lastName)
                            nationality.text = requireContext().getString(R.string.nationality,
                                data.nationality)
                            dateOfBorn.text = requireContext().getString(R.string.date_of_born,
                                data.dateOfBorn)
                            numRankingTitles.text =
                                requireContext().getString(R.string.ranking_titles,
                                    data.numRankingTitles)
                            numMaximums.text = requireContext().getString(R.string.maximums_done,
                                data.numMaximums)
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


