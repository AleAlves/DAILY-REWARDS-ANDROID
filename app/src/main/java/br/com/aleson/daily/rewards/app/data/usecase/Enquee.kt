package br.com.aleson.daily.rewards.app.data.usecase

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Enqueue<T>(private val complete: (T?) -> Unit, private val error: () -> Unit) :
    Callback<T?> {
    override fun onFailure(call: Call<T?>, t: Throwable) {
        error()
    }

    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        if (response.isSuccessful) complete(response.body()) else error()
    }
}