package br.com.aleson.daily.rewards.app.feature.login.model

import com.google.gson.annotations.SerializedName

data class UserKeyChain(
    @SerializedName("AESKey")
    val AESKey: String,
    @SerializedName("AESSalt")
    val AESSalt: String,
    @SerializedName("AESIV")
    val AESIV: String
)