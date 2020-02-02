package br.com.aleson.daily.rewards.app.feature.login.data

import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey

interface RemoteDataSource {

    fun requestPublicKeyCallback(onResponse: (PublicKey?) -> Unit, onError: () -> Unit)

}