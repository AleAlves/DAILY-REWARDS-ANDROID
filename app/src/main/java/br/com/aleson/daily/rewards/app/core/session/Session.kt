package br.com.aleson.daily.rewards.app.core.session

import br.com.aleson.daily.rewards.app.feature.login.model.SessionToken
import br.com.aleson.daily.rewards.app.feature.login.model.User

class Session {

    var user: User? = null
    private var sessionToken: SessionToken? = null

    fun setSessionToken(sessionToken: SessionToken) {
        this.sessionToken = sessionToken
    }

    fun getSessionToken(): SessionToken? {
        return sessionToken
    }

    companion object {

        private var session: Session? = null

        fun getInstance(): Session {
            if (session == null)
                session = Session()
            return session as Session
        }

    }


}