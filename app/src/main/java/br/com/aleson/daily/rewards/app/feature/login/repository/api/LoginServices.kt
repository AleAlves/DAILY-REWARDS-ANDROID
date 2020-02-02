package br.com.aleson.daily.rewards.app.feature.login.repository.api

import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import retrofit2.Call
import retrofit2.http.GET

interface LoginServices {

    @GET("api/v1/public-key")
    fun getPublicKey(): Call<HTTPResponse<PublicKey>>

}