package br.com.aleson.daily.rewards.app.feature.login.di.injectors

import br.com.aleson.daily.rewards.app.feature.login.repository.api.APIServiceClient
import br.com.aleson.daily.rewards.app.feature.login.repository.api.LoginServices
import br.com.aleson.daily.rewards.app.feature.login.di.SERVER_URL
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class APIsProvider {

    companion object {

        fun publicKeyService(): LoginServices? {
            return APIServiceClient.createService(
                SERVER_URL,
                makeOkHttpClient(),
                LoginServices::class.java
            )
        }

        private fun makeOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
    }
}