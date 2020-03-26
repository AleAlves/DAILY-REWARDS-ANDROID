package br.com.aleson.daily.rewards.app.feature.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.aleson.daily.rewards.app.core.base.BaseViewModel
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetGroupsUseCase
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetTasksUseCase
import br.com.aleson.daily.rewards.app.feature.home.view.viewstate.HomeViewEvent

class HomeViewModel(
    private var getTasksUseCase: GetTasksUseCase,
    private val getGroupsUseCase: GetGroupsUseCase
) : BaseViewModel() {

    val event = MutableLiveData<HomeViewEvent>()

    override fun setup() {

    }

    override fun onError() {
        Log.d("", "")
    }

    fun getTasks() {
        getTasksUseCase.execute(
            onResponse = { event.value = HomeViewEvent.OnLoadTasks(it?.tasks) },
            onError = { onError() })
    }

    fun getGroups() {
        getGroupsUseCase.execute(
            onResponse = { event.value = HomeViewEvent.OnLoadGroups(it?.groups) },
            onError = { onError() }
        )
    }

}