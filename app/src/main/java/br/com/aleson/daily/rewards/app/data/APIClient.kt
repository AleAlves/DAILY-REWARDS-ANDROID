package br.com.aleson.daily.rewards.app.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {
    /**
     * Method to start a new service
     * @param serviceClass API to use
     * @param <S>
     * @return Retrofit client created
    </S> */
    fun <S> createService(baseURL: String, httpClient: OkHttpClient, serviceClass: Class<S>): S? {
        val builder = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
        return builder.build().create(serviceClass)
    }
}