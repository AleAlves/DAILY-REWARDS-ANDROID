package br.com.aleson.daily.rewards.app.di

import br.com.aleson.daily.rewards.app.data.repository.Repository
import br.com.aleson.daily.rewards.app.data.usecase.UseCaseHandler
import br.com.aleson.daily.rewards.app.data.usecase.UseCaseThreadPoolScheduler
import br.com.aleson.daily.rewards.app.di.factory.LoginViewModelFactory
import br.com.aleson.daily.rewards.app.di.injectors.APIs
import br.com.aleson.daily.rewards.app.di.injectors.UseCases

const val SERVER_URL = "http://192.168.0.18:8084/"

class Injector {

    companion object {

        private var repository: Repository = Repository(APIs.publicKeyService())

        fun provideLoginViewModelFactory(): LoginViewModelFactory {
            return LoginViewModelFactory(provideUseCaseHandler(), UseCases.providePulbicKeyCase(repository))
        }

        fun provideUseCaseHandler(): UseCaseHandler {
            val threadPool = UseCaseThreadPoolScheduler()
            return UseCaseHandler(threadPool)
        }
    }

}