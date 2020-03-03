package br.com.aleson.daily.rewards.app.feature.home.usecase

import br.com.aleson.daily.rewards.app.feature.home.model.Group
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks
import br.com.aleson.daily.rewards.app.feature.home.repository.HomeRepository

class GetGroupsUseCase(private val homeRepository: HomeRepository) {

    fun get(onResponse: (List<Group>?) -> Unit, onError: () -> Unit) {

        homeRepository.requestGroupsCallback(onResponse, onError)
    }

}