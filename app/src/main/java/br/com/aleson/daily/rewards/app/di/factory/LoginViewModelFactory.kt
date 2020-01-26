package br.com.aleson.daily.rewards.app.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aleson.daily.rewards.app.data.usecase.UseCaseHandler
import br.com.aleson.daily.rewards.app.domain.usecases.PublicKeyUseCase
import br.com.aleson.daily.rewards.app.presenter.LoginViewModel


class LoginViewModelFactory(
    private var useCaseHandler: UseCaseHandler,
    private val publicKeyCase: PublicKeyUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = LoginViewModel(
        useCaseHandler,
        publicKeyCase
    ) as T
}
