package com.thescore.android

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher


/**
 * Created by Royal Lachinov on 2020-05-02.
 */
class RecyclerViewAction {

    fun clickChildViewWithId(id: Int): ViewAction? {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(View::class.java)
            }

            override fun perform(uiController: UiController?, view: View?) {
                val v: View = view!!.findViewById(id)
                v.performClick()
            }

        }
    }
}