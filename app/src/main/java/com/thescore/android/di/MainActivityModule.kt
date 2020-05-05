package com.thescore.android.di

import com.thescore.android.api.network.ApiClient
import com.thescore.android.api.service.TeamsApiService
import dagger.Module
import dagger.Provides

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

@Module(subcomponents = [MainActivityComponent::class])
class MainActivityModule {

    @TheScoreSingletonAnnotation
    @Provides
    fun getBaseApiService(apiClient: ApiClient): TeamsApiService {
        return apiClient.getRetrofitInstance().create(TeamsApiService::class.java)
    }
}