package com.thescore.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.thescore.android.databinding.FragmentPlayersBinding
import com.thescore.android.model.Team
import com.thescore.android.ui.adapters.PlayersAdapter

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

class PlayersFragment : BaseFragment() {

    private lateinit var binding: FragmentPlayersBinding
    private val playersBinding get() = binding

    private lateinit var playerAdapter: PlayersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return playersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playersBinding.progressBarPlayers.visibility = View.VISIBLE
        playerAdapter = PlayersAdapter()
        playersBinding.recyclerViewPlayers.adapter = playerAdapter

        teamsViewModel.teamLiveData.observe(viewLifecycleOwner, playerObserver)
    }

    private val playerObserver = Observer<Team> { team ->
        playersBinding.team = team
        playerAdapter.updateData(team.players)
        playersBinding.progressBarPlayers.visibility = View.GONE
    }
}
