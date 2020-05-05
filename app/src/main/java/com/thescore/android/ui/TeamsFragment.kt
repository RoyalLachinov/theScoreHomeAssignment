package com.thescore.android.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import com.thescore.android.R
import com.thescore.android.constants.CoreConstants
import com.thescore.android.databinding.FragmentTeamsBinding
import com.thescore.android.model.BaseModel
import com.thescore.android.model.Team
import com.thescore.android.ui.adapters.BaseAdapter
import com.thescore.android.ui.adapters.TeamsAdapter

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

class TeamsFragment : BaseFragment() {

    private lateinit var binding: FragmentTeamsBinding
    private val teamsBinding get() = binding
    private lateinit var teamsAdapter: TeamsAdapter
    private var teams: MutableList<Team>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamsBinding.inflate(inflater, container, false)
        teamsAdapter = TeamsAdapter()
        return teamsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamsBinding.progressBarTeam.visibility = View.VISIBLE
        teamsBinding.recyclerViewTeams.adapter = teamsAdapter

        teamsAdapter.setListener(object : BaseAdapter.ItemClickListener {
            override fun onItemClicked(baseModel: BaseModel) {
                baseModel as Team
                teamsViewModel.saveTeam(baseModel)
                navController.navigate(R.id.action_teamsFragment_to_playersFragment)
            }

        })

        teamsViewModel.teamsLiveData.observe(viewLifecycleOwner, teamsObserver)
    }

    private var teamsObserver = Observer<Any> { teamsLiveData ->

        when (teamsLiveData) {
            is MutableList<*> -> {
                teams = teamsLiveData.filterIsInstance<Team>().toMutableList()
                teamsAdapter.updateData(teams!!)
            }
            else -> {
                val errorMessage = teamsLiveData as String

                Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_LONG).show()
            }
        }
        teamsBinding.progressBarTeam.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.teams_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @SuppressLint("LogConditional")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        sharedPreferencesStorage.saveIntegerValue(CoreConstants.SELECTED_TEAM_MENU, item.itemId)
        Log.d(
            CoreConstants.SELECTED_TEAM_MENU,
            sharedPreferencesStorage.getIntegerValue(CoreConstants.SELECTED_TEAM_MENU).toString()
        )
        if (item.itemId != R.id.filter_teams)
            teamsViewModel.sortTeamsLiveData(item.itemId)

        return super.onOptionsItemSelected(item)
    }

}
