package br.com.aleson.daily.rewards.app.di.injectors

import br.com.aleson.daily.rewards.app.data.APIClient
import br.com.aleson.daily.rewards.app.data.api.LoginServices
import br.com.aleson.daily.rewards.app.di.SERVER_URL
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class APIs {

    companion object {

        fun publicKeyService(): LoginServices? {
            return APIClient.createService(
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