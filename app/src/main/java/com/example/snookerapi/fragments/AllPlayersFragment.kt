package com.example.snookerapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snookerapi.*
import com.example.snookerapi.adapters.PlayerAdapter
import com.example.snookerapi.databinding.FragmentAllPlayersBinding
import com.example.snookerapi.extensions.addHorizontalSpace
import com.example.snookerapi.model.PagingData
import com.example.snookerapi.model.Player
import com.example.snookerapi.retrofit.SnookerService
import retrofit2.*

class AllPlayersFragment : Fragment() {

    private var _binding: FragmentAllPlayersBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter by lazy {
        PlayerAdapter(
            context = requireContext(),
            onPlayerClicked = {
                findNavController()
                    .navigate(
                        AllPlayersFragmentDirections
                            .toPlayerProfile(
                                it.id,
                            ))
            }
        )
    }

    private val currentPlayers = mutableListOf<Player>()

    private var currentRequest: Call<List<Player>>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentAllPlayersBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            swipeRefresh.setOnRefreshListener {
                executeRequest {
                    swipeRefresh.isRefreshing = false
                }
            }

            val linearLayoutManager = LinearLayoutManager(
                view.context, LinearLayoutManager.VERTICAL, false
            )

            recyclerView.adapter = adapter
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.addHorizontalSpace(requireContext().resources.getDimensionPixelSize(R.dimen.space_res))

            executeRequest()

            toolbar
                .menu
                .findItem(R.id.action_search)
                .actionView
                .let { it as SearchView }
                .setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(query: String): Boolean {
                        val sortedPlayers = currentPlayers
                            .filter {
                                it.firstName.contains(query) || it.lastName.contains(query)
                            }
                        adapter.submitList(sortedPlayers
                            .map { PagingData.Item(it) })
                        return true
                    }
                })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentRequest?.cancel()
        _binding = null
    }

    private fun executeRequest(
        onRequestFinished: () -> Unit = {},
    ) {

        val finishRequest = {
            onRequestFinished()
            currentRequest = null
        }

        currentRequest?.cancel()
        currentRequest = SnookerService.snookerApi
            .getPlayers(SEASON_TO_SHOW)
            .apply {
                enqueue(object : Callback<List<Player>> {
                    override fun onResponse(
                        call: Call<List<Player>>,
                        response: Response<List<Player>>,
                    ) {
                        if (response.isSuccessful) {
                            val players = response.body() ?: return
                            currentPlayers.clear()
                            currentPlayers.addAll(players)
                            val items = players.map { PagingData.Item(it) } + PagingData.Loading
                            adapter.submitList(items)

                        } else {
                            handleException(HttpException(response))
                        }
                        finishRequest()
                    }

                    override fun onFailure(call: Call<List<Player>>, t: Throwable) {
                        if (!call.isCanceled) {
                            handleException(t)
                        }
                        finishRequest()
                    }
                })
            }
    }

    private fun handleException(e: Throwable) {
        Toast.makeText(requireContext(), e.message ?: "Something went wrong", Toast.LENGTH_SHORT)
            .show()
    }

    companion object {

        private const val SEASON_TO_SHOW = 2021

    }
}