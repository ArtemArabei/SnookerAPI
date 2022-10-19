package com.example.snookerapi.presentation.ui.chooseYear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.snookerapi.R
import com.example.snookerapi.databinding.FragmentChooseYearBinding
import com.example.snookerapi.presentation.extensions.getYearOrSetError
import org.koin.android.ext.android.inject

class ChooseYearFragment : Fragment() {

    private var _binding: FragmentChooseYearBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by inject<ChooseYearViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentChooseYearBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = findNavController()

        with(binding) {
            buttonShowPlayers.setOnClickListener {
                when (val yearToShow = containerYear.getYearOrSetError()) {
                    in 2005..2022 -> {
                        viewModel.onButtonShowPlayersClicked(controller, yearToShow!!)
                    }
                    else -> Toast.makeText(requireContext(),
                        getString(R.string.wrong_year_chosen),
                        Toast.LENGTH_SHORT).show()
                }
            }
            buttonShowEvents.setOnClickListener {
                when (val yearToShow = containerYear.getYearOrSetError()) {
                    in 2005..2022 -> {
                        viewModel.onButtonShowEventsClicked(controller, yearToShow!!)
                    }
                    else -> Toast.makeText(requireContext(),
                        getString(R.string.wrong_year_chosen),
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}