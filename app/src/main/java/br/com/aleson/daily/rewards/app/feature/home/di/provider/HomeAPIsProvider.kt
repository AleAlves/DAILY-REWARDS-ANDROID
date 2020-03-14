package br.com.aleson.daily.rewards.app.feature.home.di.provider

import br.com.aleson.daily.rewards.app.core.api.APIServiceClient
import br.com.aleson.daily.rewards.app.feature.home.repository.service.HomeServices

class HomeAPIsProvider {

    companion object {

        fun services(server: String): HomeServices? {

            return APIServiceClient.createService(
                server,
                HomeServices::class.java
            )
        }
    }
}