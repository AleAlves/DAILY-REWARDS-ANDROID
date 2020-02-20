package br.com.aleson.daily.rewards.app.core.repository

import br.com.aleson.daily.rewards.app.feature.login.model.SessionToken

open class BaseRepository {

    private var sessionToken: SessionToken? = null

    fun setSessionToken(sessionToken: SessionToken?) {
        this.sessionToken = sessionToken
    }

    fun getSessionToken(): SessionToken? {
        return sessionToken
    }
}