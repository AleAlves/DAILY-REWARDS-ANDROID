package br.com.aleson.daily.rewards.app.feature.login.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aleson.daily.rewards.app.feature.login.usecase.PublicKeyUseCase
import br.com.aleson.daily.rewards.app.feature.login.viewmodel.LoginViewModel


class LoginViewModelFactory(
    private val publicKeyCase: PublicKeyUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        LoginViewModel(publicKeyCase) as T
}
