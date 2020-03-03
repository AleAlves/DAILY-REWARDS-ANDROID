package br.com.aleson.daily.rewards.app.feature.home.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetGroupsUseCase
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetTasksUseCase
import br.com.aleson.daily.rewards.app.feature.home.viewmodel.HomeViewModel

class HomeViewModelFactory(
    private val getTasksUseCase: GetTasksUseCase,
    private val getGroupsUseCase: GetGroupsUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeViewModel(getTasksUseCase, getGroupsUseCase) as T
}