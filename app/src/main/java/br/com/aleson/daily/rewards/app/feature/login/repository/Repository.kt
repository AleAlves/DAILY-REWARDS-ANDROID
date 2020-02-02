package br.com.aleson.daily.rewards.app.feature.login.repository

import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.feature.login.data.RemoteDataSource
import br.com.aleson.daily.rewards.app.feature.login.repository.api.LoginServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val loginServices: LoginServices?) : RemoteDataSource {

    override fun requestPublicKeyCallback(onResponse: (PublicKey?) -> Unit, onError: () -> Unit) {

        val callback = object : Callback<HTTPResponse<PublicKey>> {
            override fun onFailure(
                call: Call<HTTPResponse<PublicKey>>,
                t: Throwable
            ) {
                onError()
            }

            override fun onResponse(
                call: Call<HTTPResponse<PublicKey>>,
                response: Response<HTTPResponse<PublicKey>>
            ) {
                onResponse(response.body()?.data as PublicKey)
            }
        }

        loginServices?.getPublicKey()?.enqueue(callback)
    }

}