package br.com.aleson.daily.rewards.app.data.api

import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.domain.security.PublicKey
import retrofit2.Call
import retrofit2.http.GET

interface LoginServices {

    @GET("api/v1/public-key")
    fun getPublicKey(): Call<HTTPResponse<PublicKey>?>

}