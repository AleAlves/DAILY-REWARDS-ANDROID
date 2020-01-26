package br.com.aleson.daily.rewards.app.domain.usecases

import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.data.repository.Repository
import br.com.aleson.daily.rewards.app.data.usecase.Enqueue
import br.com.aleson.daily.rewards.app.data.usecase.UseCase
import br.com.aleson.daily.rewards.app.data.usecase.UseCaseRequest
import br.com.aleson.daily.rewards.app.data.usecase.UseCaseResponse
import br.com.aleson.daily.rewards.app.domain.security.PublicKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetPublicKeyRequest : UseCaseRequest

class GetPublicKeyResponse(val response: HTTPResponse<PublicKey>?) : UseCaseResponse

class PublicKeyUseCase(private val repository: Repository) :
    UseCase<GetPublicKeyRequest, GetPublicKeyResponse>() {

    override fun executeUseCase(
        request: GetPublicKeyRequest,
        success: (GetPublicKeyResponse) -> Unit,
        error: () -> Unit
    ) {
        val callback = Enqueue<HTTPResponse<PublicKey>?>(
            {
                success(GetPublicKeyResponse(it))
            },
            {
                error()
            })
        repository.requestPublicKeyCallback(request, callback)
    }

}