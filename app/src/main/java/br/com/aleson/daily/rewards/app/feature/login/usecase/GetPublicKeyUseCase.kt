package br.com.aleson.daily.rewards.app.feature.login.usecase

import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.daily.rewards.app.feature.login.repository.LoginRepository

class GetPublicKeynRequest : UseCaseRequest

class GetPublicKeyResponse(val publicKey: PublicKey?) : UseCaseResponse

class GetPublicKeyUseCase(private val loginRepository: LoginRepository) :
    BaseUseCase<GetPublicKeynRequest, GetPublicKeyResponse>() {

    override fun execute(onResponse: (GetPublicKeyResponse?) -> Unit, onError: () -> Unit) {
        loginRepository.requestPublicKeyCallback(onResponse, onError)
    }
}