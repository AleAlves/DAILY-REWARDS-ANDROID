package br.com.aleson.daily.rewards.app.feature.login.usecase

import br.com.aleson.daily.rewards.app.feature.login.model.SessionToken
import br.com.aleson.daily.rewards.app.feature.login.model.User
import br.com.aleson.daily.rewards.app.feature.login.repository.Repository
import com.google.firebase.auth.FirebaseUser

class LoginUseCase(private val repository: Repository) {

    fun login(
        accessToken: String,
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
        repository.requestLogin(accessToken, user, onResponse, onError)
    }

}