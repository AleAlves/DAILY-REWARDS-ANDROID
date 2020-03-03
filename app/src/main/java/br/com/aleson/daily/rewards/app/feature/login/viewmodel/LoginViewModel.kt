package br.com.aleson.daily.rewards.app.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.daily.rewards.app.core.base.BaseViewModel
import br.com.aleson.daily.rewards.app.feature.login.usecase.GetAccessTokenUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.CallLoginUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.GetPublicKeyUseCase
import br.com.aleson.daily.rewards.app.feature.login.view.viewstate.LoginViewEvent
import br.com.aleson.daily.rewards.app.feature.login.view.viewstate.LoginViewState
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(
    private val getPublicKeyCase: GetPublicKeyUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val callLoginUseCase: CallLoginUseCase
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
        getPublicKeyCase.getPublicKey(
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
        getAccessTokenUseCase.getAccessToken(uid, publicKey.publicKey,
            onResponse = { accessToken ->
                val token = accessToken?.accessToken
                login(token)
            },
            onError = {
                onError()
            })
    }

    private fun login(token: String?) {
        callLoginUseCase.login(
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