package br.com.aleson.daily.rewards.app.feature.home.di.injector

import br.com.aleson.core.tools.coretools.CoreToolsBuilder
import br.com.aleson.daily.rewards.app.core.di.BaseProvider
import br.com.aleson.daily.rewards.app.feature.home.di.factory.HomeViewModelFactory
import br.com.aleson.daily.rewards.app.feature.home.di.provider.HomeAPIsProvider
import br.com.aleson.daily.rewards.app.feature.home.di.provider.HomeUseCasesProvider
import br.com.aleson.daily.rewards.app.feature.home.repository.HomeRepository

class HomeInjector : BaseProvider() {

    companion object {

        private var core: CoreToolsBuilder = CoreToolsBuilder.Builder().server("http://192.168.0.11:8084/").build()

        private var homeRepository: HomeRepository = HomeRepository(HomeAPIsProvider.services(getSeverUrl()))

        fun provideHomeViewModelFactory(): HomeViewModelFactory {

            return HomeViewModelFactory(
                HomeUseCasesProvider.provideGetTasksCase(homeRepository),
                HomeUseCasesProvider.provideGetGroupsCase(homeRepository)
            )
        }
    }

}