package br.com.aleson.daily.rewards.app.feature.home.repository.data

import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks
import retrofit2.Callback


interface HomeRemoteDataSource {

    fun requestTasksCallback(
        onResponse: (List<Tasks>?) -> Unit,
        onError: () -> Unit
    )

    fun requestTasksCallback(
        onResponse: Callback<HTTPResponse<List<Tasks>>>,
        onError: () -> Unit
    )
}