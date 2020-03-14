package br.com.aleson.daily.rewards.app.feature.login.view.viewstate

import java.util.*


sealed class LoginViewState {

    class OnError(val error: Exception) : LoginViewState()

    object ShowLoading : LoginViewState()

    object HideLoading : LoginViewState()
}
