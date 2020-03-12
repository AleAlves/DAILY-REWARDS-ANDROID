package br.com.aleson.daily.rewards.app.feature.login.usecase


interface UseCaseRequest

interface UseCaseResponse

abstract class BaseUseCase<Request : UseCaseRequest, Response : UseCaseResponse> {

    lateinit var request: Request
    lateinit var onResponse: ((Response?) -> Unit)
    lateinit var onError: (() -> Unit)

    fun run() {
        execute(onResponse, onError)
    }

    abstract fun execute(
        onResponse: (Response?) -> Unit,
        onError: () -> Unit
    )
}