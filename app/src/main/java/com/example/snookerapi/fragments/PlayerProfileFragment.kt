package com.example.snookerapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.example.snookerapi.R
import com.example.snookerapi.databinding.FragmentPlayerProfileBinding
import com.example.snookerapi.model.PlayerProfile
import com.example.snookerapi.retrofit.SnookerService
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerProfileFragment : Fragment() {

    private var _binding: FragmentPlayerProfileBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args by navArgs<PlayerProfileFragmentArgs>()

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

        loadPlayerProfile()

        with(binding) {
            toolbar.setupWithNavController(findNavController())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadPlayerProfile() {
        SnookerService.snookerApi.getPlayerProfile(args.id)
            .enqueue(object : Callback<List<PlayerProfile>> {
                override fun onResponse(
                    call: Call<List<PlayerProfile>>,
                    response: Response<List<PlayerProfile>>,
                ) {
                    if (response.isSuccessful) {
                        val playerProfileList = response.body() ?: return
                        val playerProfile = playerProfileList[0]
                        with(binding) {
                            if (playerProfile.photoUrl != "") {
                                playerPhoto.load(playerProfile.photoUrl)
                            } else {
                                playerPhoto.load(R.drawable.no_photo)
                            }
                            firstName.text = requireContext().getString(R.string.first_name,
                                playerProfile.firstName)
                            lastName.text = requireContext().getString(R.string.last_name,
                                playerProfile.lastName)
                            nationality.text = requireContext().getString(R.string.nationality,
                                playerProfile.nationality)
                            dateOfBorn.text = requireContext().getString(R.string.date_of_born,
                                playerProfile.dateOfBorn)
                            numRankingTitles.text =
                                requireContext().getString(R.string.ranking_titles,
                                    playerProfile.numRankingTitles)
                            numMaximums.text = requireContext().getString(R.string.maximums_done,
                                playerProfile.numMaximums)
                        }
                    } else {
                        handleErrors(response.errorBody()?.string() ?: "")
                    }
                }

                override fun onFailure(call: Call<List<PlayerProfile>>, t: Throwable) {
                    handleErrors(t.message ?: "")
                }
            })
    }

    private fun handleErrors(errorMessage: String) {
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT)
            .setAction(android.R.string.ok) {}
            .show()
    }
}


