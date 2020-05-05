package com.thescore.android

import android.app.Application
import com.thescore.android.di.AppComponent
import com.thescore.android.di.DaggerAppComponent

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

class TheScoreApplication : Application() {

    lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        applicationComponent = DaggerAppComponent.factory().create(applicationContext)
        super.onCreate()
    }
}