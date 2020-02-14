package br.com.aleson.daily.rewards.app.feature.login.view.viewstate

import br.com.aleson.daily.rewards.app.feature.login.model.SessionToken

sealed class LoginViewEvent {

    data class OnReceiveSessionToken(val accessToken: SessionToken?) : LoginViewEvent()

}