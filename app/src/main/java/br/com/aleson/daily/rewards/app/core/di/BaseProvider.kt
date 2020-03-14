package br.com.aleson.daily.rewards.app.core.di


open abstract class BaseProvider {


    companion object {

        private var serverUrl = "https://base-node-typescript-api.herokuapp.com/"

        fun setServerUrl(url: String) {
            this.serverUrl = url
        }

        fun getSeverUrl(): String = serverUrl
    }


}
