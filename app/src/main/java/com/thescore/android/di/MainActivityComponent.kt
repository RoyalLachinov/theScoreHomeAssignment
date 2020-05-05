package com.thescore.android.di

import com.thescore.android.ui.MainActivity
import dagger.Subcomponent

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

@Subcomponent
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}