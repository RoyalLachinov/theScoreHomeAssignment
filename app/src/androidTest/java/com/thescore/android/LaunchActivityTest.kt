package com.thescore.android

import android.app.Activity
import android.app.Instrumentation
import android.widget.ImageView
import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.thescore.android.ui.LaunchActivity
import com.thescore.android.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Royal Lachinov on 2020-05-02.
 */

@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class LaunchActivityTest {

    @get:Rule
    val launchActivityTestRule = ActivityTestRule(LaunchActivity::class.java)
    lateinit var launchActivity: LaunchActivity


    @Before
    @Throws(Exception::class)
    fun setUp() {
        launchActivity = launchActivityTestRule.activity

    }

    @Test
    fun testSplashActivityUI() {
        val imgSplash: ImageView = launchActivity.findViewById(R.id.image_view_splash)
        Assert.assertNotNull(imgSplash)


    /*    CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
           *//* val mainActivity: IntentsTestRule<MainActivity> =
                IntentsTestRule(MainActivity::class.java)
            val intent = Intent()
            mainActivity.launchActivity(intent)*//*

            val monitor: Instrumentation.ActivityMonitor =
                InstrumentationRegistry.getInstrumentation().addMonitor(MainActivity::class.java.name, null, false)
            val secondActivity: Activity =
                InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(monitor, 5000)
            Assert.assertNotNull(secondActivity)


        }*/

    }
}