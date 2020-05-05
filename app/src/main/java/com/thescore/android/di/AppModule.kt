package com.thescore.android.di


import com.thescore.android.api.network.ApiClient
import com.thescore.android.constants.UserManager
import dagger.Module
import dagger.Provides

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

@Module
class AppModule {

    @Provides
    @TheScoreSingletonAnnotation
    fun provideApiClient(userManager: UserManager): ApiClient {
        return ApiClient(userManager)
    }
}