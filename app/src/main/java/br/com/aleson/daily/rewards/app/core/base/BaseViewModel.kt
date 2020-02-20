package br.com.aleson.daily.rewards.app.core.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract fun setup()

    abstract fun onError()
}