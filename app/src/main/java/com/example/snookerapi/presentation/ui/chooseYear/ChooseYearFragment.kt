package com.example.snookerapi.presentation.ui.chooseYear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.snookerapi.R
import com.example.snookerapi.databinding.FragmentChooseYearBinding
import com.example.snookerapi.presentation.extensions.getProperValue
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseYearFragment : Fragment() {

    private var _binding: FragmentChooseYearBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by viewModel<ChooseYearViewModel>()

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

        val valueChecker: (Int?) -> String? = {
            when (it) {
                null -> getString(R.string.field_is_empty)
                in 2005..2022 -> null
                else -> getString(R.string.wrong_year_chosen)
            }
        }

        val controller = findNavController()

        with(binding) {
            buttonShowPlayers.setOnClickListener {

                val result = containerYear.getProperValue(valueChecker)

                if (result != null) {
                    viewModel.onButtonShowPlayersClicked(controller, result)
                }
            }
            buttonShowEvents.setOnClickListener {

                val result = containerYear.getProperValue(valueChecker)

                if (result != null) {
                    viewModel.onButtonShowEventsClicked(controller, result)
                }
            }

            buttonShowMap.setOnClickListener {

                    viewModel.onButtonShowMapClicked(controller)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}