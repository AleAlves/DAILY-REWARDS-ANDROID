package br.com.aleson.daily.rewards.app.data

import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.data.usecase.Enqueue
import br.com.aleson.daily.rewards.app.domain.security.PublicKey
import br.com.aleson.daily.rewards.app.domain.usecases.GetPublicKeyRequest
import br.com.aleson.daily.rewards.app.domain.usecases.GetPublicKeyResponse
import br.com.aleson.daily.rewards.app.domain.usecases.PublicKeyUseCase

interface RemoteDataSource {

    fun requestPublicKeyCallback(request: GetPublicKeyRequest, callback: Enqueue<HTTPResponse<PublicKey>?>)

}