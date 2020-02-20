package br.com.aleson.daily.rewards.app.feature.login.usecase

import br.com.aleson.daily.rewards.app.core.session.Session
import br.com.aleson.daily.rewards.app.feature.login.model.SessionToken
import br.com.aleson.daily.rewards.app.feature.login.model.User
import br.com.aleson.daily.rewards.app.feature.login.repository.LoginRepository
import com.google.firebase.auth.FirebaseUser

class LoginUseCase(private val loginRepository: LoginRepository) {

    fun login(
        accessToken: String?,
        firebaseUser: FirebaseUser?,
        onResponse: (SessionToken?) -> Unit,
        onError: () -> Unit
    ) {
        val user = User(
            firebaseUser?.displayName,
            firebaseUser?.email,
            firebaseUser?.photoUrl.toString(),
            firebaseUser?.uid
        )


        var response: (SessionToken?) -> Unit = { sessionToken ->
            sessionToken?.let { it1 -> Session.getInstance()?.setSessionToken(it1) }
            onResponse(sessionToken)
        }

        accessToken?.let { loginRepository.requestLogin(it, user, response, onError) }
    }

}