package com.thescore.android.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

data class Player(
    @SerializedName("id")
    var playerId: Int,
    @SerializedName("first_name")
    var playerName: String,
    @SerializedName("last_name")
    var playerLastName: String,
    @SerializedName("position")
    var position: String,
    @SerializedName("number")
    var number: String
):BaseModel()