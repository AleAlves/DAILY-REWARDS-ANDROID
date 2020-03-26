package br.com.aleson.daily.rewards.app.feature.home.repository.data

import br.com.aleson.daily.rewards.app.feature.home.usecase.GetGroupsUseCaseResponse
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetTasksUseCaseResponse


interface HomeRemoteDataSource {

    fun requestTasksCallback(
        onResponse: (GetTasksUseCaseResponse?) -> Unit,
        onError: () -> Unit
    )

    fun requestGroupsCallback(
        onResponse: (GetGroupsUseCaseResponse?) -> Unit,
        onError: () -> Unit
    )
}