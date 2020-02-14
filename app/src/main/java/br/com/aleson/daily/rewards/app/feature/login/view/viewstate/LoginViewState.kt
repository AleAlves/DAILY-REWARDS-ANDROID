package br.com.aleson.daily.rewards.app.feature.login.view.viewstate


sealed class LoginViewState {

    data class OnError(val error: Exception) : LoginViewState()

    data class ShowLoading(val dissmisable: Boolean) : LoginViewState()

    data class HideLoading(val dissmisable: Boolean) : LoginViewState()
}
