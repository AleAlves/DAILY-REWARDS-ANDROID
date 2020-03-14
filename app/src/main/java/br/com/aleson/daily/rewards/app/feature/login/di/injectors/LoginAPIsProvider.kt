package br.com.aleson.daily.rewards.app.feature.login.di.injectors

import br.com.aleson.daily.rewards.app.core.api.APIServiceClient
import br.com.aleson.daily.rewards.app.feature.login.repository.service.LoginServices

class LoginAPIsProvider {

    companion object {

        fun loginServices(): LoginServices? {
            return APIServiceClient.createService(
                LoginServices::class.java
            )
        }
    }
}