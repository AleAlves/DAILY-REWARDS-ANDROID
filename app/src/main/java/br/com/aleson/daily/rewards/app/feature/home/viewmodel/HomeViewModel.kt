package br.com.aleson.daily.rewards.app.feature.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.aleson.daily.rewards.app.core.base.BaseViewModel
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetTasksUseCase

class HomeViewModel(var publicKeyCase: GetTasksUseCase) : BaseViewModel() {

    var list: MutableLiveData<List<Tasks>>? = MutableLiveData()

    override fun setup() {
        publicKeyCase.get(
            onResponse = {
                list?.value = it
            }, onError = {
                Log.d("", "")
            })
    }

    override fun onError() {
        Log.d("", "")
    }

}