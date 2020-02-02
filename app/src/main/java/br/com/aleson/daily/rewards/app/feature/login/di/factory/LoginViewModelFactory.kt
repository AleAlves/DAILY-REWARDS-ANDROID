package br.com.aleson.daily.rewards.app.feature.login.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aleson.daily.rewards.app.feature.login.usecase.AccessTokenUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.LoginUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.PublicKeyUseCase
import br.com.aleson.daily.rewards.app.feature.login.viewmodel.LoginViewModel


class LoginViewModelFactory(
    private val publicKeyCase: PublicKeyUseCase,
    private val accessTokenUseCase: AccessTokenUseCase,
    private val loginUseCase: LoginUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        LoginViewModel(publicKeyCase, accessTokenUseCase, loginUseCase) as T
}
