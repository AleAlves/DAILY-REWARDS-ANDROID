package br.com.aleson.daily.rewards.app.feature.login.repository.api

import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPRequest
import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.feature.login.model.AccessToken
import br.com.aleson.daily.rewards.app.feature.login.model.SessionToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginServices {

    @GET("api/v1/public-key")
    fun getPublicKey(): Call<HTTPResponse<PublicKey>>


    @POST("api/v1/access-token")
    fun getAccessToken(@Body body: HTTPRequest<String>): Call<HTTPResponse<AccessToken>>


    @POST("api/v1/login")
    fun login(@Header("access-token") accessToken: String, @Body body: HTTPRequest<String>): Call<HTTPResponse<SessionToken>>


}