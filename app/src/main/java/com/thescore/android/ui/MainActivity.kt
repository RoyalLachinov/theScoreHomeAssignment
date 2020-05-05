package com.thescore.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.thescore.android.R
import com.thescore.android.TheScoreApplication
import com.thescore.android.constants.SharedPreferenceStorage
import com.thescore.android.di.MainActivityComponent
import com.thescore.android.viewmodel.TeamsViewModel
import javax.inject.Inject

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var teamsViewModel: TeamsViewModel
    @Inject
    lateinit var sharedPreferencesStorage: SharedPreferenceStorage

    lateinit var mainActivityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityComponent =
            (applicationContext as TheScoreApplication).applicationComponent.mainActivityComponent()
                .create()
        mainActivityComponent.inject(this)


        setContentView(R.layout.activity_main)
    }

    override fun onStop() {
        super.onStop()
        viewModelStore.clear()
    }
}
