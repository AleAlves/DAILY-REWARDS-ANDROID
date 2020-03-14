package br.com.aleson.daily.rewards.app.feature.login.di.injectors

import br.com.aleson.daily.rewards.app.core.api.APIServiceClient
import br.com.aleson.daily.rewards.app.feature.login.repository.service.LoginServices

class LoginAPIsProvider {

    companion object {

        fun loginServices(server: String): LoginServices? {
            return APIServiceClient.createService(
                server,
                LoginServices::class.java
            )
        }
    }
}