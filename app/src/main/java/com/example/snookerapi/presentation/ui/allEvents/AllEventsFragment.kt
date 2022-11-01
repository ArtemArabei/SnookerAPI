package com.example.snookerapi.presentation.ui.allEvents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snookerapi.R
import com.example.snookerapi.databinding.FragmentAllEventsBinding
import com.example.snookerapi.presentation.extensions.addHorizontalSpace
import com.example.snookerapi.presentation.model.Lce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AllEventsFragment : Fragment() {

    private var _binding: FragmentAllEventsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args: AllEventsFragmentArgs by navArgs()

    private val viewModel by viewModel<AllEventsViewModel> { parametersOf(args.year) }

    private val adapter by lazy {
        EventAdapter(
            context = requireContext(),
            onEventClicked = {
                findNavController()
                    .navigate(
                        AllEventsFragmentDirections
                            .toEventProfile(
                                it.id,
                            ))
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentAllEventsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            val linearLayoutManager = LinearLayoutManager(
                view.context, LinearLayoutManager.VERTICAL, false
            )

            recyclerView.adapter = adapter
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.addHorizontalSpace(R.dimen.space_res)

            swipeRefresh.setOnRefreshListener {
                refreshList()
            }

            toolbar
                .menu
                .findItem(R.id.action_search)
                .actionView
                .let { it as SearchView }
                .setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String): Boolean = false

                    override fun onQueryTextChange(query: String): Boolean {
                        viewModel.onQueryChanged(query)
                        return true
                    }
                })

            toolbar.setupWithNavController(findNavController())

            viewModel
                .snookerDataFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .onEach { swipeRefresh.isRefreshing = false }
                .onEach { lce ->
                    when (lce) {
                        Lce.Loading -> {
                            loadingProgress.isVisible = true
                        }
                        is Lce.Content -> {
                            loadingProgress.isVisible = false
                            recyclerView.isVisible = true
                            adapter.submitList(lce.data)
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
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshList() {
        viewModel.onRefreshed()
        binding.swipeRefresh.isRefreshing = false
    }

}