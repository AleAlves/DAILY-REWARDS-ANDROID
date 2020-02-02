package br.com.aleson.daily.rewards.app.feature.login.di.injectors

import br.com.aleson.daily.rewards.app.feature.login.repository.Repository
import br.com.aleson.daily.rewards.app.feature.login.usecase.AccessTokenUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.LoginUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.PublicKeyUseCase

class LoginUseCasesProvider {

    companion object {

        fun providePulbicKeyCase(repository: Repository): PublicKeyUseCase {
            return PublicKeyUseCase(repository)
        }

        fun provideAccessTokenUseCase(repository: Repository): AccessTokenUseCase {
            return AccessTokenUseCase(repository)
        }

        fun provideLoginUseCase(repository: Repository): LoginUseCase {
            return LoginUseCase(repository)
        }
    }
}