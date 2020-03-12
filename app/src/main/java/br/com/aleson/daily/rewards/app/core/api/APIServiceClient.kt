package br.com.aleson.daily.rewards.app.core.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val SERVER_URL = "http://192.168.0.11:8084/"


object APIServiceClient {

    fun <S> createService(baseURL: String, serviceClass: Class<S>): S? {
        val builder = Retrofit.Builder()
            .baseUrl(baseURL)
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