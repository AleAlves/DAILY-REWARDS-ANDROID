package br.com.aleson.daily.rewards.app.feature.home.di.provider

import br.com.aleson.daily.rewards.app.feature.home.repository.HomeRepository
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetGroupsUseCase
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetTasksUseCase

class HomeUseCasesProvider {

    companion object {

        fun provideGetTasksCase(homeRepository: HomeRepository): GetTasksUseCase {
            return GetTasksUseCase(homeRepository)
        }

        fun provideGetGroupsCase(homeRepository: HomeRepository): GetGroupsUseCase {
            return GetGroupsUseCase(homeRepository)
        }
    }
}