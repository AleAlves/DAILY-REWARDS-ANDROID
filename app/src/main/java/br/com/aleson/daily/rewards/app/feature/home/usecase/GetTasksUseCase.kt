package br.com.aleson.daily.rewards.app.feature.home.usecase

import br.com.aleson.daily.rewards.app.feature.home.model.Tasks
import br.com.aleson.daily.rewards.app.feature.home.repository.HomeRepository

class GetTasksUseCase(private val homeRepository: HomeRepository) {

    fun get(onResponse: (List<Tasks>?) -> Unit, onError: () -> Unit) {

        homeRepository.requestTasksCallback(onResponse, onError)
    }

}