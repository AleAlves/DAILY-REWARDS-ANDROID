package br.com.aleson.daily.rewards.app.di.injectors

import br.com.aleson.daily.rewards.app.data.repository.Repository
import br.com.aleson.daily.rewards.app.domain.usecases.PublicKeyUseCase

class UseCases {

    companion object{

        fun providePulbicKeyCase(repository: Repository): PublicKeyUseCase {
            return PublicKeyUseCase(repository)
        }
    }
}