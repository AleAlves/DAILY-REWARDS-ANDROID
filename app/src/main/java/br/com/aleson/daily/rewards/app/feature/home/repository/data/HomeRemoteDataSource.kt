package br.com.aleson.daily.rewards.app.feature.home.repository.data

import br.com.aleson.daily.rewards.app.feature.home.model.Tasks


interface HomeRemoteDataSource {

    fun requestTasksCallback(
        onResponse: (List<Tasks>?) -> Unit,
        onError: () -> Unit
    )
}