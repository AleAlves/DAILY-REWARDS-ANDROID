package br.com.aleson.daily.rewards.app.domain.security

import com.google.gson.annotations.SerializedName

class PublicKey(
    @SerializedName("publicKey")
    val publicKey: String
)
