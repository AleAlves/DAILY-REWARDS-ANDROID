package br.com.aleson.daily.rewards.app.feature.home.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetTasksUseCase
import br.com.aleson.daily.rewards.app.feature.home.viewmodel.HomeViewModel

class HomeViewModelFactory(
    private val publicKeyCase: GetTasksUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        HomeViewModel(publicKeyCase) as T
}