package br.com.aleson.daily.rewards.app.feature.login.di.injectors

import br.com.aleson.daily.rewards.app.feature.login.repository.LoginRepository
import br.com.aleson.daily.rewards.app.feature.login.usecase.GetAccessTokenUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.CallLoginUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.GetPublicKeyUseCase

class LoginUseCasesProvider {

    companion object {

        fun providePulbicKeyCase(loginRepository: LoginRepository): GetPublicKeyUseCase {
            return GetPublicKeyUseCase(loginRepository)
        }

        fun provideAccessTokenUseCase(loginRepository: LoginRepository): GetAccessTokenUseCase {
            return GetAccessTokenUseCase(loginRepository)
        }

        fun provideLoginUseCase(loginRepository: LoginRepository): CallLoginUseCase {
            return CallLoginUseCase(loginRepository)
        }
    }
}