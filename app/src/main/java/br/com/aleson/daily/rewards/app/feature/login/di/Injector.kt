package br.com.aleson.daily.rewards.app.feature.login.di

import br.com.aleson.daily.rewards.app.feature.login.di.factory.LoginViewModelFactory
import br.com.aleson.daily.rewards.app.feature.login.di.injectors.APIsProvider
import br.com.aleson.daily.rewards.app.feature.login.di.injectors.UseCasesProvider
import br.com.aleson.daily.rewards.app.feature.login.repository.Repository

const val SERVER_URL = "http://192.168.0.11:8084/"

class Injector {

    companion object {

        private var repository: Repository =
            Repository(APIsProvider.publicKeyService())

        fun provideLoginViewModelFactory(): LoginViewModelFactory {
            return LoginViewModelFactory(UseCasesProvider.providePulbicKeyCase(repository))
        }
    }

}