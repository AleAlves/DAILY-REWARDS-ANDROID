package br.com.aleson.daily.rewards.app.feature.login.di

import br.com.aleson.core.tools.coretools.CoreToolsBuilder
import br.com.aleson.daily.rewards.app.feature.login.di.factory.LoginViewModelFactory
import br.com.aleson.daily.rewards.app.feature.login.di.injectors.APIsProvider
import br.com.aleson.daily.rewards.app.feature.login.di.injectors.LoginUseCasesProvider
import br.com.aleson.daily.rewards.app.feature.login.repository.Repository

const val SERVER_URL = "http://192.168.0.11:8084/"

class Injector {

    companion object {

        private var core: CoreToolsBuilder =
            CoreToolsBuilder.Builder().server("http://192.168.0.18:8084/").build()
        private var repository: Repository = Repository(APIsProvider.publicKeyService(), core)

        fun provideLoginViewModelFactory(): LoginViewModelFactory {
            return LoginViewModelFactory(
                LoginUseCasesProvider.providePulbicKeyCase(repository),
                LoginUseCasesProvider.provideAccessTokenUseCase(repository),
                LoginUseCasesProvider.provideLoginUseCase(repository)
            )
        }
    }

}