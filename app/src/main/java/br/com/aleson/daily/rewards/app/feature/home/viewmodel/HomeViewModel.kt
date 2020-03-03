package br.com.aleson.daily.rewards.app.feature.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.aleson.daily.rewards.app.core.base.BaseViewModel
import br.com.aleson.daily.rewards.app.feature.home.model.Group
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetGroupsUseCase
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetTasksUseCase

class HomeViewModel(
    private var getTasksUseCase: GetTasksUseCase,
    private val getGroupsUseCase: GetGroupsUseCase
) : BaseViewModel() {

    var taskslist: MutableLiveData<List<Tasks>>? = MutableLiveData()
    var groupslist: MutableLiveData<List<Group>>? = MutableLiveData()

    override fun setup() {

    }

    override fun onError() {
        Log.d("", "")
    }

    fun getTasks() {
        getTasksUseCase.get(
            onResponse = {
                taskslist?.value = it
            },
            onError = {
                Log.d("", "")
            })
    }

    fun getGroups() {
        getGroupsUseCase.get(
            onResponse = {
                groupslist?.value = it
            },
            onError = {

            }
        )
    }

}