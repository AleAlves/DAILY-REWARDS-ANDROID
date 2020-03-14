package br.com.aleson.daily.rewards.app.core.data

import android.content.Context
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.util.JsonParser
import br.com.aleson.daily.rewards.app.feature.login.usecase.EnviromentUseCaseResponse
import com.google.gson.reflect.TypeToken
import java.util.*


class BaseLocalDataSourceImpl(val context: Context) : BaseLocalDataSource {

    override fun getEnviroments(): EnviromentUseCaseResponse {
        val env = context.resources.openRawResource(R.raw.enviroments)
        val jsonString: String = Scanner(env).useDelimiter("\\A").next()
        return JsonParser().parseJsonToObject(jsonString, object : TypeToken<EnviromentUseCaseResponse>() {}.type)
    }


}