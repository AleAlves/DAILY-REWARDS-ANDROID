package br.com.aleson.daily.rewards.app.feature.login.di.injectors

import br.com.aleson.daily.rewards.app.feature.login.repository.Repository
import br.com.aleson.daily.rewards.app.feature.login.usecase.PublicKeyUseCase

class UseCasesProvider {

    companion object {

        fun providePulbicKeyCase(repository: Repository): PublicKeyUseCase {
            return PublicKeyUseCase(repository)
        }
    }
}