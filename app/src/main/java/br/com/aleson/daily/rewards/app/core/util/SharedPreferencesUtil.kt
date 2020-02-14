package br.com.aleson.daily.rewards.app.core.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil {
    private val SHARED_ID = "DAILY_REWARDS_SHA_PREF"

    companion object {
        fun getInstance(): SharedPreferencesUtil = SharedPreferencesUtil()
    }

    private fun getSharedPreferences(applicationContext: Context): SharedPreferences {
        return applicationContext.getSharedPreferences(SHARED_ID, Context.MODE_PRIVATE)
    }
}