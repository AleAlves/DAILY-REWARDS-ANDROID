package br.com.aleson.daily.rewards.app.core

import android.app.Application

class DailyRewardsApplication : Application(){

    private val server = "http://192.168.0.11:8084/"

    override fun onCreate() {
        super.onCreate()
    }

}