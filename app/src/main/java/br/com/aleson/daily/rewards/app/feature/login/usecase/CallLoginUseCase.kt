package br.com.aleson.daily.rewards.app.feature.login.usecase

import br.com.aleson.daily.rewards.app.feature.login.model.SessionToken
import br.com.aleson.daily.rewards.app.feature.login.model.User
import br.com.aleson.daily.rewards.app.feature.login.repository.LoginRepository
import com.google.firebase.auth.FirebaseUser


class CallLoginRequest(val firebaseUser: FirebaseUser?, val accessToken: String?) : UseCaseRequest

class CallLoginResponse(val sessionToken: SessionToken?) : UseCaseResponse

class CallLoginUseCase(private val loginRepository: LoginRepository) :
    BaseUseCase<CallLoginRequest, CallLoginResponse>() {

    override fun execute(
        onResponse: (CallLoginResponse?) -> Unit,
        onError: () -> Unit
    ) {

        loginRepository.requestLogin(
            request.accessToken.toString(),
            mapUser(request.firebaseUser),
            onResponse,
            onError
        )
    }

    private fun mapUser(firebaseUser: FirebaseUser?): User {
        return User(
            request.firebaseUser?.displayName,
            request.firebaseUser?.email,
            request.firebaseUser?.toString(),
            request.firebaseUser?.uid
        )
    }
}