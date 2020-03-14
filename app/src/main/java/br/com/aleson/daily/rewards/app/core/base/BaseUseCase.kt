package br.com.aleson.daily.rewards.app.core.base


interface UseCaseRequest

interface UseCaseResponse

abstract class BaseUseCase<Request : UseCaseRequest, Response : UseCaseResponse> {

    lateinit var request: Request
    lateinit var onResponse: ((Response?) -> Unit)
    lateinit var onError: (() -> Unit)

    abstract fun execute(
        onResponse: (Response?) -> Unit,
        onError: () -> Unit
    )
}