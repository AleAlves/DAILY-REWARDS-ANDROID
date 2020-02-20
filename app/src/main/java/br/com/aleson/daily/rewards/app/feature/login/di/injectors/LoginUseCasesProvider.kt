package br.com.aleson.daily.rewards.app.feature.login.di.injectors

import br.com.aleson.daily.rewards.app.feature.login.repository.LoginRepository
import br.com.aleson.daily.rewards.app.feature.login.usecase.AccessTokenUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.LoginUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.PublicKeyUseCase

class LoginUseCasesProvider {

    companion object {

        fun providePulbicKeyCase(loginRepository: LoginRepository): PublicKeyUseCase {
            return PublicKeyUseCase(loginRepository)
        }

        fun provideAccessTokenUseCase(loginRepository: LoginRepository): AccessTokenUseCase {
            return AccessTokenUseCase(loginRepository)
        }

        fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
            return LoginUseCase(loginRepository)
        }
    }
}