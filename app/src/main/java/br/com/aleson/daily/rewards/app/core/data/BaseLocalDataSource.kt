package br.com.aleson.daily.rewards.app.core.data

import br.com.aleson.daily.rewards.app.feature.login.usecase.EnviromentUseCaseResponse

interface BaseLocalDataSource {

    fun getEnviroments() : EnviromentUseCaseResponse
}