package br.com.aleson.daily.rewards.app.feature.login.usecase

import br.com.aleson.daily.rewards.app.core.base.BaseUseCase
import br.com.aleson.daily.rewards.app.core.base.UseCaseRequest
import br.com.aleson.daily.rewards.app.core.base.UseCaseResponse
import br.com.aleson.daily.rewards.app.feature.login.model.AccessToken
import br.com.aleson.daily.rewards.app.feature.login.repository.LoginRepository


class GetAccessTokenRequest(val uid: String?, val publicKey: String?) : UseCaseRequest

class GetAccessTokenResponse(val accessToken: AccessToken?) : UseCaseResponse

class GetAccessTokenUseCase(private val loginRepository: LoginRepository) :
    BaseUseCase<GetAccessTokenRequest, GetAccessTokenResponse>() {

    override fun execute(onResponse: (GetAccessTokenResponse?) -> Unit, onError: () -> Unit) {

        loginRepository.requestAccessToken(
            request.uid.toString(),
            request.publicKey.toString(),
            onResponse,
            onError
        )
    }
}