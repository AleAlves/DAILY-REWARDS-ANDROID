package br.com.aleson.daily.rewards.app.presenter.base

import androidx.lifecycle.ViewModel
import br.com.aleson.daily.rewards.app.data.usecase.UseCaseHandler

abstract class BaseViewModel(
    protected val mUseCaseHandler: UseCaseHandler?
) : ViewModel() {

    abstract fun init()

    abstract fun onError()
}