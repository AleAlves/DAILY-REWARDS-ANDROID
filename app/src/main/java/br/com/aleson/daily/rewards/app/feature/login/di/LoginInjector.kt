package br.com.aleson.daily.rewards.app.feature.login.di

import android.content.Context
import br.com.aleson.core.tools.coretools.CoreToolsBuilder
import br.com.aleson.daily.rewards.app.core.data.BaseLocalDataSourceImpl
import br.com.aleson.daily.rewards.app.feature.login.di.factory.LoginViewModelFactory
import br.com.aleson.daily.rewards.app.feature.login.di.injectors.LoginAPIsProvider
import br.com.aleson.daily.rewards.app.feature.login.di.injectors.LoginUseCasesProvider
import br.com.aleson.daily.rewards.app.feature.login.repository.LoginRepository


class LoginInjector {

    companion object {

        private var core: CoreToolsBuilder = CoreToolsBuilder.Builder().server("http://192.168.0.11:8084/").build()
        var loginRepository: LoginRepository? = null

        fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {

            loginRepository =
                LoginRepository(LoginAPIsProvider.loginServices(), BaseLocalDataSourceImpl(context), core)

            return LoginViewModelFactory(
                LoginUseCasesProvider.providePulbicKeyCase(loginRepository as LoginRepository),
                LoginUseCasesProvider.provideAccessTokenUseCase(loginRepository as LoginRepository),
                LoginUseCasesProvider.provideLoginUseCase(loginRepository as LoginRepository),
                LoginUseCasesProvider.provideEnviromentUseCase(loginRepository as LoginRepository)
            )
        }
    }

}