package br.com.aleson.daily.rewards.app.feature.home.repository

import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.core.repository.BaseRepository
import br.com.aleson.daily.rewards.app.core.session.Session
import br.com.aleson.daily.rewards.app.feature.home.model.Group
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks
import br.com.aleson.daily.rewards.app.feature.home.repository.data.HomeRemoteDataSource
import br.com.aleson.daily.rewards.app.feature.home.repository.service.HomeServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(var homeServices: HomeServices?) : BaseRepository(),
    HomeRemoteDataSource {

    override fun requestTasksCallback(onResponse: (List<Tasks>?) -> Unit, onError: () -> Unit) {

        val callback = object : Callback<HTTPResponse<List<Tasks>>> {

            override fun onFailure(
                call: Call<HTTPResponse<List<Tasks>>>,
                t: Throwable
            ) {
                onError()
            }

            override fun onResponse(
                call: Call<HTTPResponse<List<Tasks>>>,
                response: Response<HTTPResponse<List<Tasks>>>
            ) {
                onResponse(response.body()?.data)
            }
        }

        var token = Session.getInstance()?.getSessionToken()

        token?.sessionToken?.let { homeServices?.getTasks(it)?.enqueue(callback) }
    }

    override fun requestGroupsCallback(onResponse: (List<Group>?) -> Unit, onError: () -> Unit) {

        val callback = object : Callback<HTTPResponse<List<Group>>> {

            override fun onFailure(
                call: Call<HTTPResponse<List<Group>>>,
                t: Throwable
            ) {
                onError()
            }

            override fun onResponse(
                call: Call<HTTPResponse<List<Group>>>,
                response: Response<HTTPResponse<List<Group>>>
            ) {
                onResponse(response.body()?.data)
            }
        }
        var token = Session.getInstance()?.getSessionToken()

        token?.sessionToken?.let { homeServices?.getGroups(it)?.enqueue(callback) }
    }

}