package br.com.aleson.daily.rewards.app.feature.login.usecase

import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.daily.rewards.app.feature.login.repository.Repository


class PublicKeyUseCase(private val repository: Repository) {

    fun getPublicKey(onResponse: (PublicKey?) -> Unit, onError: () -> Unit) {

        var response: (PublicKey?) -> Unit = {
            onResponse(it)
        }

        repository.requestPublicKeyCallback(response, onError)
    }
}