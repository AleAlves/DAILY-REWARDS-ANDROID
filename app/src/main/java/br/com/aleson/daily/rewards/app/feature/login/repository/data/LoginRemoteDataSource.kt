package br.com.aleson.daily.rewards.app.feature.login.repository.data

import br.com.aleson.daily.rewards.app.feature.login.model.User
import br.com.aleson.daily.rewards.app.feature.login.usecase.CallLoginResponse
import br.com.aleson.daily.rewards.app.feature.login.usecase.GetAccessTokenResponse
import br.com.aleson.daily.rewards.app.feature.login.usecase.GetPublicKeyResponse

interface LoginRemoteDataSource {

    fun requestPublicKeyCallback(
        onResponse: (GetPublicKeyResponse?) -> Unit,
        onError: () -> Unit
    )

    fun requestAccessToken(
        firebaseToken: String,
        publicKey: String,
        onResponse: (GetAccessTokenResponse?) -> Unit,
        onError: () -> Unit
    )

    fun requestLogin(
        accessToken: String,
        user: User,
        onResponse: (CallLoginResponse?) -> Unit,
        onError: () -> Unit
    )
}