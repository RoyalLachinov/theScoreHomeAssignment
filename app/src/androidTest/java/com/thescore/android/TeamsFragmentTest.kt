package com.thescore.android


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.PositionAssertions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.thescore.android.model.Team
import com.thescore.android.repository.TeamsRepo
import com.thescore.android.ui.MainActivity
import com.thescore.android.ui.TeamsFragment
import com.thescore.android.ui.adapters.TeamsAdapter
import com.thescore.android.viewmodel.TeamsViewModel
import kotlinx.coroutines.*
import org.hamcrest.core.AllOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import javax.inject.Inject

/**
 * Created by Royal Lachinov on 2020-05-01.
 */

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class TeamsFragmentTest {

    @get:Rule
    val mainActivityTestRule = ActivityTestRule(MainActivity::class.java)
    lateinit var mainActivity: MainActivity


    @Before
    @Throws(Exception::class)
    fun setUp() {
        mainActivity = mainActivityTestRule.activity
    }

    @Test
    fun onCreateView() {
        //Checking views nullability
        Assert.assertNotNull(ViewMatchers.withId(R.id.layout_team_header))
        Assert.assertNotNull(ViewMatchers.withId(R.id.txt_team_name_header))
        Assert.assertNotNull(ViewMatchers.withId(R.id.txt_wins_header))
        Assert.assertNotNull(ViewMatchers.withId(R.id.txt_losses_header))
        Assert.assertNotNull(ViewMatchers.withId(R.id.recycler_view_teams))
        Assert.assertNotNull(ViewMatchers.withId(R.id.progress_bar_team))

        Assert.assertNotNull(ViewMatchers.withId(R.id.txt_count))
        Assert.assertNotNull(ViewMatchers.withId(R.id.txt_team_name))
        Assert.assertNotNull(ViewMatchers.withId(R.id.txt_wins))
        Assert.assertNotNull(ViewMatchers.withId(R.id.txt_losses))
    }

    @Test
    fun onViewCreated() {
        Espresso.onView(ViewMatchers.withId(R.id.txt_losses_header))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_teams))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_teams)).check(
            PositionAssertions.isCompletelyBelow(
                ViewMatchers.withId(R.id.layout_team_header)
            )
        )

        //Checking view visibility and text
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.txt_team_name_header),
                ViewMatchers.withText(R.string.name)
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.txt_wins_header),
                ViewMatchers.withText(R.string.wins)
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.txt_losses_header),
                ViewMatchers.withText(R.string.losses)
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))

        testWinsMenuClick()

        scrollTeamsListView(20)

        testLossesMenuClick()

        scrollTeamsListView(0)

        clickOneOfTheTeams()

    }


    fun testWinsMenuClick() {
        //Click menu
        Espresso.onView(ViewMatchers.withId(R.id.filter_teams))
            //.check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
            .perform(ViewActions.click())

        //Open menu
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            Espresso.openContextualActionModeOverflowMenu()
        }

        // Click the losses item.
        Espresso.onView(ViewMatchers.withText(R.string.wins))
            .perform(ViewActions.click())

        // Verify that we have really clicked on the icon by checking
        // the text content.
        Espresso.onView(ViewMatchers.withText(R.string.wins))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.wins)))
    }

    fun testLossesMenuClick() {
        //Click menu
        Espresso.onView(ViewMatchers.withId(R.id.filter_teams))
            .perform(ViewActions.click())

        //Open menu
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            Espresso.openContextualActionModeOverflowMenu()
        }

        // Click the losses item.
        Espresso.onView(ViewMatchers.withText(R.string.losses))
            .perform(ViewActions.click())

        // Verify that we have really clicked on the icon by checking
        // the text content.
        Espresso.onView(ViewMatchers.withText(R.string.losses))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.losses)))
    }


    fun clickOneOfTheTeams() {

        /**
        TODO() to perform recyclerViewOnClick action follow the below steps
        Open the Settings app.
        Scroll to the bottom and select About phone.
        Scroll to the bottom and tap Build number 7 times.
        Return to the previous screen to find Developer options near the bottom.
        Access Developer Options from Settings app, and under the Drawing section, switch all of the following options to Animation Off:

        Window animation scale
        Transition animation scale
        Animator duration scale
         */

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_teams))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<TeamsAdapter.ItemViewHolder>
                    (0, RecyclerViewAction().clickChildViewWithId(R.id.layout_item_team))
            )
    }

    @Test
    fun testNavigationToInPlayerScreen() {

       /* CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            val mockNavController =
                Mockito.mock(NavHostFragment.findNavController(TeamsFragment())::class.java)

            // Create a graphical FragmentScenario for the TitleScreen
            val titleScenario = launchFragmentInContainer<TeamsFragment>()
            titleScenario.onFragment { fragment ->
                if (fragment.isAdded) {
                    Espresso.onView(ViewMatchers.withId(R.id.layout_item_team))
                        .perform(ViewActions.click())
                    Mockito.verify(mockNavController)
                        .navigate(R.id.action_teamsFragment_to_playersFragment)
                }
            }
        }*/

    }


    fun scrollTeamsListView(position: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_teams))
            .perform(RecyclerViewActions.scrollToPosition<TeamsAdapter.ItemViewHolder>(position))
    }

}