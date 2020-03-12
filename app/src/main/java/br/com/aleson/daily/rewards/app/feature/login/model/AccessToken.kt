package br.com.aleson.daily.rewards.app.feature.login.model

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("accessToken")
    val value: String
)