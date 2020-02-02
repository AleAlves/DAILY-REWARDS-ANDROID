package br.com.aleson.daily.rewards.app.feature.login.usecase

import br.com.aleson.daily.rewards.app.feature.login.model.AccessToken
import br.com.aleson.daily.rewards.app.feature.login.repository.Repository

class AccessTokenUseCase(private val repository: Repository) {

    fun getAccessToken(
        uid: String,
        publicKey: String, //TODO inject publick key in the core module
        onResponse: (AccessToken?) -> Unit,
        onError: () -> Unit
    ) {

        repository.requestAccessToken(
            uid,
            publicKey,
            onResponse,
            onError
        )
    }
}