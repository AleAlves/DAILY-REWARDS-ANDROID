package br.com.aleson.daily.rewards.app.feature.home.usecase

import br.com.aleson.daily.rewards.app.core.base.BaseUseCase
import br.com.aleson.daily.rewards.app.core.base.UseCaseRequest
import br.com.aleson.daily.rewards.app.core.base.UseCaseResponse
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks
import br.com.aleson.daily.rewards.app.feature.home.repository.HomeRepository

class GetTasksUseCaseRequest : UseCaseRequest

class GetTasksUseCaseResponse(var tasks: List<Tasks>?) : UseCaseResponse

class GetTasksUseCase(private val homeRepository: HomeRepository) :
    BaseUseCase<GetTasksUseCaseRequest, GetTasksUseCaseResponse>() {

    override fun execute(onResponse: (GetTasksUseCaseResponse?) -> Unit, onError: () -> Unit) {
        homeRepository.requestTasksCallback(onResponse, onError)
    }

}