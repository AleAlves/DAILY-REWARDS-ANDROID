package br.com.aleson.daily.rewards.app.data.usecase

interface UseCaseRequest

interface UseCaseResponse

abstract class UseCase<Rq : UseCaseRequest, Rs : UseCaseResponse> {

    lateinit var request: Rq
    lateinit var success: ((Rs) -> Unit)
    lateinit var error: (() -> Unit)

    fun run() {
        executeUseCase(request, success, error)
    }

    abstract fun executeUseCase(request: Rq, success: ((Rs) -> Unit), error: (() -> Unit))
}