package br.com.aleson.daily.rewards.app.feature.home.di.provider

import br.com.aleson.daily.rewards.app.core.api.APIServiceClient
import br.com.aleson.daily.rewards.app.core.di.BaseProvider
import br.com.aleson.daily.rewards.app.core.di.SERVER_URL
import br.com.aleson.daily.rewards.app.feature.home.repository.service.HomeServices

class HomeAPIsProvider : BaseProvider() {

    companion object {

        fun services(): HomeServices? {

            return APIServiceClient.createService(
                SERVER_URL,
                HomeServices::class.java
            )
        }
    }
}