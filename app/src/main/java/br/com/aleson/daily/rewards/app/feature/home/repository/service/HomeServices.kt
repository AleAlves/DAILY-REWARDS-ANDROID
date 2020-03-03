package br.com.aleson.daily.rewards.app.feature.home.repository.service

import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.feature.home.model.Group
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeServices {

    @GET("api/v1/task")
    fun getTasks(@Header("access-token") accessToken: String): Call<HTTPResponse<List<Tasks>>>

    @GET("api/v1/group")
    fun getGroups(@Header("access-token") accessToken: String): Call<HTTPResponse<List<Group>>>

}