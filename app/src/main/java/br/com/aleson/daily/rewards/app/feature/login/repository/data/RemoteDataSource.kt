package br.com.aleson.daily.rewards.app.feature.login.repository.data

import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.daily.rewards.app.feature.login.model.AccessToken
import br.com.aleson.daily.rewards.app.feature.login.model.SessionToken
import br.com.aleson.daily.rewards.app.feature.login.model.User

interface RemoteDataSource {

    fun requestPublicKeyCallback(
        onResponse: (PublicKey?) -> Unit,
        onError: () -> Unit
    )

    fun requestAccessToken(
        firebaseToken: String,
        publicKey: String,
        onResponse: (AccessToken?) -> Unit,
        onError: () -> Unit
    )

    fun requestLogin(
        accessToken: String,
        user: User,
        onResponse: (SessionToken?) -> Unit,
        onError: () -> Unit
    )
}