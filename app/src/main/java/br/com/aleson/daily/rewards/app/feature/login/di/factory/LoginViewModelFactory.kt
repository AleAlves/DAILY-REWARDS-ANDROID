package br.com.aleson.daily.rewards.app.feature.login.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aleson.daily.rewards.app.feature.login.usecase.GetAccessTokenUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.CallLoginUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.GetPublicKeyUseCase
import br.com.aleson.daily.rewards.app.feature.login.viewmodel.LoginViewModel


class LoginViewModelFactory(
    private val getPublicKeyCase: GetPublicKeyUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val callLoginUseCase: CallLoginUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        LoginViewModel(getPublicKeyCase, getAccessTokenUseCase, callLoginUseCase) as T
}
