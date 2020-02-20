package br.com.aleson.daily.rewards.app.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.daily.rewards.app.core.base.BaseViewModel
import br.com.aleson.daily.rewards.app.feature.login.usecase.AccessTokenUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.LoginUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.PublicKeyUseCase
import br.com.aleson.daily.rewards.app.feature.login.view.viewstate.LoginViewEvent
import br.com.aleson.daily.rewards.app.feature.login.view.viewstate.LoginViewState
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(
    private val publicKeyCase: PublicKeyUseCase,
    private val accessTokenUseCase: AccessTokenUseCase,
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    var user: MutableLiveData<FirebaseUser>? = MutableLiveData()

    private val state = MutableLiveData<LoginViewState>()
    private val event = MutableLiveData<LoginViewEvent>()
    val viewState: LiveData<LoginViewState> = state
    val viewEvent: LiveData<LoginViewEvent> = event

    override fun setup() {

    }

    override fun onError() {
        this.state.value = LoginViewState.OnError(Exception("Error"))
    }

    fun loadPublicKey(uid: String) {
        this.state.value = LoginViewState.ShowLoading(false)
        publicKeyCase.getPublicKey(
            onResponse = { publicKey ->
                if (publicKey != null) {
                    getAccessToken(uid, publicKey)
                }
            },
            onError = { onError() })
    }

    fun getAccessToken(
        uid: String,
        publicKey: PublicKey
    ) {
        accessTokenUseCase.getAccessToken(uid, publicKey.publicKey,
            onResponse = { accessToken ->
                val token = accessToken?.accessToken
                login(token)
            },
            onError = {
                onError()
            })
    }

    private fun login(token: String?) {
        loginUseCase.login(
            token,
            user?.value,
            onResponse = { sessionToken ->
                this.event.value = LoginViewEvent.OnReceiveSessionToken(sessionToken)
                this.state.value = LoginViewState.HideLoading(false)
            }, onError = {
                onError()
            })
    }

}