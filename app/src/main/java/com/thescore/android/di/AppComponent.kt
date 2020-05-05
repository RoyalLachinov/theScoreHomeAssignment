package com.thescore.android.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

@TheScoreSingletonAnnotation
@Component(
    modules = [AppModule::class, MainActivityModule::class]
)
interface AppComponent {

    fun mainActivityComponent(): MainActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}