package com.thescore.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thescore.android.R
import com.thescore.android.api.network.ApiResponse
import com.thescore.android.api.network.Coroutines
import com.thescore.android.model.Team
import com.thescore.android.repository.TeamsRepo
import kotlinx.coroutines.Job
import javax.inject.Inject

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

class TeamsViewModel @Inject constructor(private val teamsRepo: TeamsRepo) : ViewModel() {

    private lateinit var teamsViewModelJob: Job

    private val teamsMutableLiveData = MutableLiveData<Any>()
    val teamsLiveData: LiveData<Any> get() = teamsMutableLiveData

    private val teamMutableLiveData = MutableLiveData<Team>()
    val teamLiveData: LiveData<Team> get() = teamMutableLiveData

    init {
        getTeams()
    }

     private fun getTeams() {
        teamsViewModelJob = Coroutines.ioThenMain(
            {
                teamsRepo.getTeams()
            },
            {
                it as ApiResponse
                when {
                    it.successBody != null -> {

                        teamsMutableLiveData.value = it.successBody
                    }
                    it.errorBody != null -> {
                        teamsMutableLiveData.value = it.errorBody
                    }
                }

            })

    }

    fun sortTeamsLiveData(itemId:Int){
        var teamsList  = teamsMutableLiveData.value as MutableList<*>
        teamsList = teamsList.filterIsInstance<Team>().toMutableList()
        when (itemId) {
            R.id.filter_a_to_z -> {
                teamsList.sortBy { team -> team.fullName }
            }
            R.id.filter_z_to_a -> {
                teamsList.sortByDescending { it.fullName }
            }
            R.id.filter_wins -> {
                teamsList.sortWith(Comparator { team1: Team, team2: Team -> team2.wins - team1.wins })
            }
            R.id.filter_loses -> {
                teamsList.sortWith(Comparator { team1: Team, team2: Team -> team2.losses - team1.losses })
            }
        }

        teamsMutableLiveData.value = teamsList
    }

    fun saveTeam(team: Team) {
        teamMutableLiveData.value = team
    }

    /**
     * Cancel the job when view destroyed.
     */
    public override fun onCleared() {
        super.onCleared()
        if (::teamsViewModelJob.isInitialized)
            teamsViewModelJob.cancel()

    }
}