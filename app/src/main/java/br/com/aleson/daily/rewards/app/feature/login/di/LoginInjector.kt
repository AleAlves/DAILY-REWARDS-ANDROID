package br.com.aleson.daily.rewards.app.feature.login.di

import br.com.aleson.core.tools.coretools.CoreToolsBuilder
import br.com.aleson.daily.rewards.app.feature.login.di.factory.LoginViewModelFactory
import br.com.aleson.daily.rewards.app.feature.login.di.injectors.LoginAPIsProvider
import br.com.aleson.daily.rewards.app.feature.login.di.injectors.LoginUseCasesProvider
import br.com.aleson.daily.rewards.app.feature.login.repository.LoginRepository


class LoginInjector {

    companion object {

        private var core: CoreToolsBuilder =
                CoreToolsBuilder.Builder().server("http://192.168.0.11:8084/").build()
        private var loginRepository: LoginRepository =
            LoginRepository(LoginAPIsProvider.loginServices(), core)


        fun provideLoginViewModelFactory(): LoginViewModelFactory {
            return LoginViewModelFactory(
                LoginUseCasesProvider.providePulbicKeyCase(loginRepository),
                LoginUseCasesProvider.provideAccessTokenUseCase(loginRepository),
                LoginUseCasesProvider.provideLoginUseCase(loginRepository)
            )
        }
    }

}