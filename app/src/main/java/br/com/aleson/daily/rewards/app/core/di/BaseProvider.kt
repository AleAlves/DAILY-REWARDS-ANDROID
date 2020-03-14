package br.com.aleson.daily.rewards.app.core.di


open abstract class BaseProvider {


    companion object {

        private var serverUrl = "http://192.168.0.11:8084/"

        fun setServerUrl(url: String) {
            this.serverUrl = url
        }

        fun getSeverUrl(): String = serverUrl
    }


}
