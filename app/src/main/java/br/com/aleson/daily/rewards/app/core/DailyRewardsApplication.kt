package br.com.aleson.daily.rewards.app.core

import android.app.Application

class DailyRewardsApplication : Application(){


    companion object {
        var serverUrl = "https://base-node-typescript-api.herokuapp.com/"
    }

    override fun onCreate() {
        super.onCreate()
    }

}