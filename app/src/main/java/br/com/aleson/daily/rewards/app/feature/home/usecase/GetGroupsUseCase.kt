package br.com.aleson.daily.rewards.app.feature.home.usecase

import br.com.aleson.daily.rewards.app.core.base.BaseUseCase
import br.com.aleson.daily.rewards.app.core.base.UseCaseRequest
import br.com.aleson.daily.rewards.app.core.base.UseCaseResponse
import br.com.aleson.daily.rewards.app.feature.home.model.Group
import br.com.aleson.daily.rewards.app.feature.home.repository.HomeRepository

class GetGroupsUseCaseRequest : UseCaseRequest

class GetGroupsUseCaseResponse(var groups: List<Group>?) : UseCaseResponse

class GetGroupsUseCase(private val homeRepository: HomeRepository) :
    BaseUseCase<GetGroupsUseCaseRequest, GetGroupsUseCaseResponse>() {

    override fun execute(onResponse: (GetGroupsUseCaseResponse?) -> Unit, onError: () -> Unit) {
        homeRepository.requestGroupsCallback(onResponse, onError)
    }

}