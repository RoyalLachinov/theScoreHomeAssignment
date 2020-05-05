package com.thescore.android.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

data class Team(
    @SerializedName("id")
    var teamId: Int,
    @SerializedName("wins")
    var wins: Int,
    @SerializedName("losses")
    var losses: Int,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("players")
    var players: MutableList<Player>
) : BaseModel()