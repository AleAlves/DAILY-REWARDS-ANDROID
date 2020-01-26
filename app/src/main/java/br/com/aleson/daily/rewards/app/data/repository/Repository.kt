package br.com.aleson.daily.rewards.app.data.repository

import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.data.RemoteDataSource
import br.com.aleson.daily.rewards.app.data.api.LoginServices
import br.com.aleson.daily.rewards.app.data.usecase.Enqueue
import br.com.aleson.daily.rewards.app.domain.security.PublicKey
import br.com.aleson.daily.rewards.app.domain.usecases.GetPublicKeyRequest
import br.com.aleson.daily.rewards.app.domain.usecases.PublicKeyUseCase

class Repository(private val loginServices: LoginServices?) : RemoteDataSource {

    override fun requestPublicKeyCallback(
        request: GetPublicKeyRequest, callback: Enqueue<HTTPResponse<PublicKey>?>
    ) {

        loginServices?.getPublicKey()?.enqueue(callback)
    }

}