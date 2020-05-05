package com.thescore.android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.thescore.android.model.Player
import com.thescore.android.model.Team
import com.thescore.android.repository.TeamsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import javax.inject.Inject

/**
 * Created by Royal Lachinov on 2020-05-02.
 */
@RunWith(MockitoJUnitRunner.Silent::class)
class TeamsViewModelTest {

    @Mock
    lateinit var teamsViewModel: TeamsViewModel

    @Mock
    lateinit var teamsRepo: TeamsRepo

    private val playerList = mutableListOf(
        Player(
            1,
            "Kadeem",
            "Allen",
            "SG",
            "45"
        ), Player(
            1,
            "Aron",
            "Baynes",
            "C",
            "46"
        )
    )

    private val team = Team(
        1,
        45
        , 20,
        "Boston Celtics",
        playerList
    )

    @Spy
    private val teamListLiveData: MutableLiveData<List<Team>> = MutableLiveData()
    @Spy
    private val teamMutableLiveData = MutableLiveData<Team>()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        CoroutineScope(Dispatchers.IO).launch {
            Mockito.`when`(teamsRepo.getTeams()).thenReturn(teamListLiveData)
        }
    }


    @Test
    fun getTeams() {
        teamListLiveData.let {it ->
            Assert.assertNotNull(it)
        }
    }


    @Test
    fun saveTeam() {
        teamsViewModel.saveTeam(team)
        CoroutineScope(Dispatchers.IO).launch {
            Mockito.`when`(teamsViewModel.teamLiveData).thenReturn(teamMutableLiveData)
        }

    }

    @Test
    fun checkSelectedTeam(){
        teamMutableLiveData.let {
            Assert.assertNotNull(it)
        }

    }

}