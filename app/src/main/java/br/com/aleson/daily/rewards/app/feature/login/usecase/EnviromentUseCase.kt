package br.com.aleson.daily.rewards.app.feature.login.usecase

import br.com.aleson.daily.rewards.app.core.base.BaseUseCase
import br.com.aleson.daily.rewards.app.core.base.UseCaseRequest
import br.com.aleson.daily.rewards.app.core.base.UseCaseResponse
import br.com.aleson.daily.rewards.app.core.model.Enviroment
import br.com.aleson.daily.rewards.app.feature.login.repository.LoginRepository

class EnviromentUseCaseRequest : UseCaseRequest

class EnviromentUseCaseResponse(val enviroments: List<Enviroment>) : UseCaseResponse

class EnviromentUseCase(private val loginRepository: LoginRepository) : BaseUseCase<EnviromentUseCaseRequest, EnviromentUseCaseResponse>() {

    override fun execute(onResponse: (EnviromentUseCaseResponse?) -> Unit, onError: () -> Unit) {
        onResponse(loginRepository.getEnviroments())
    }

}