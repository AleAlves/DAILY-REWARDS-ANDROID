package br.com.aleson.daily.rewards.app.feature.home.viewmodel

import android.util.Log
import br.com.aleson.daily.rewards.app.core.base.BaseViewModel
import br.com.aleson.daily.rewards.app.feature.home.usecase.GetTasksUseCase

class HomeViewModel(var publicKeyCase: GetTasksUseCase) : BaseViewModel() {

    override fun setup() {
        publicKeyCase.get(
            onResponse = {
                Log.d("", "")
            }, onError = {
                Log.d("", "")
            })
    }

    override fun onError() {
        Log.d("", "")
    }

}