package br.com.aleson.daily.rewards.app.feature.login.di

import android.content.Context
import br.com.aleson.core.tools.coretools.CoreToolsBuilder
import br.com.aleson.daily.rewards.app.core.data.BaseLocalDataSourceImpl
import br.com.aleson.daily.rewards.app.core.di.BaseProvider
import br.com.aleson.daily.rewards.app.feature.login.di.factory.LoginViewModelFactory
import br.com.aleson.daily.rewards.app.feature.login.di.injectors.LoginAPIsProvider
import br.com.aleson.daily.rewards.app.feature.login.di.injectors.LoginUseCasesProvider
import br.com.aleson.daily.rewards.app.feature.login.repository.LoginRepository


class LoginInjector : BaseProvider() {

    companion object {

        private var core: CoreToolsBuilder = CoreToolsBuilder.Builder().server("http://192.168.0.11:8084/").build()

        fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {

            val loginRepository = LoginRepository(LoginAPIsProvider.loginServices(getSeverUrl()), BaseLocalDataSourceImpl(context), core)

            return LoginViewModelFactory(
                LoginUseCasesProvider.providePulbicKeyCase(loginRepository),
                LoginUseCasesProvider.provideAccessTokenUseCase(loginRepository),
                LoginUseCasesProvider.provideLoginUseCase(loginRepository),
                LoginUseCasesProvider.provideEnviromentUseCase(loginRepository)
            )
        }
    }

}