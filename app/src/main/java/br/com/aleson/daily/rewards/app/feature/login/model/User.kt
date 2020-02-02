package br.com.aleson.daily.rewards.app.feature.login.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("picture")
    val picture: String?,
    @SerializedName("firebaseID")
    val firebaseID: String?
)