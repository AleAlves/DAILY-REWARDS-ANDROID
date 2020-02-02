package br.com.aleson.daily.rewards.app.feature.login.repository.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIServiceClient {

    fun <S> createService(baseURL: String, httpClient: OkHttpClient, serviceClass: Class<S>): S? {
        val builder = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
        return builder.build().create(serviceClass)
    }
}