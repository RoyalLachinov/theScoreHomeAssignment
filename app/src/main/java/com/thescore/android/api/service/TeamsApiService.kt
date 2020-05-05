package com.thescore.android.api.service

import com.thescore.android.model.Team
import com.thescore.android.constants.CoreConstants
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

interface TeamsApiService {

    @GET(CoreConstants.TEAMS_POINT)
    suspend fun getTeams():Response<MutableList<Team>>
}