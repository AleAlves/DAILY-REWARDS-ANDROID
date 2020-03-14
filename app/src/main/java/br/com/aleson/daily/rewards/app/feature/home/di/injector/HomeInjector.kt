package br.com.aleson.daily.rewards.app.feature.home.di.injector

import br.com.aleson.daily.rewards.app.feature.home.di.factory.HomeViewModelFactory
import br.com.aleson.daily.rewards.app.feature.home.di.provider.HomeAPIsProvider
import br.com.aleson.daily.rewards.app.feature.home.di.provider.HomeUseCasesProvider
import br.com.aleson.daily.rewards.app.feature.home.repository.HomeRepository

class HomeInjector {

    companion object {

        private var homeRepository: HomeRepository = HomeRepository(HomeAPIsProvider.services())

        fun provideHomeViewModelFactory(): HomeViewModelFactory {

            return HomeViewModelFactory(
                HomeUseCasesProvider.provideGetTasksCase(homeRepository),
                HomeUseCasesProvider.provideGetGroupsCase(homeRepository)
            )
        }
    }

}