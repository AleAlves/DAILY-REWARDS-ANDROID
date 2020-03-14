package br.com.aleson.daily.rewards.app.core.api

import br.com.aleson.daily.rewards.app.core.DailyRewardsApplication
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIServiceClient {

    fun <S> createService(serviceClass: Class<S>): S? {
        val builder = Retrofit.Builder()
            .baseUrl(DailyRewardsApplication.serverUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(makeOkHttpClient())
        return builder.build().create(serviceClass)
    }

    private fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}