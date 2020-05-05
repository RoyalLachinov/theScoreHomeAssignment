package com.thescore.android.repository

import com.thescore.android.api.network.ApiRequest
import com.thescore.android.api.service.TeamsApiService
import com.thescore.android.di.TheScoreSingletonAnnotation
import javax.inject.Inject

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

@TheScoreSingletonAnnotation
class TeamsRepo @Inject constructor(
    private val teamsApiService: TeamsApiService
) : ApiRequest() {

    suspend fun getTeams() = apiRequest {
        teamsApiService.getTeams()
    }
}