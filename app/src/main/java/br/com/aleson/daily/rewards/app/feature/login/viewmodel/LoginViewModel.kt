package br.com.aleson.daily.rewards.app.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.daily.rewards.app.core.base.BaseViewModel
import br.com.aleson.daily.rewards.app.feature.login.usecase.*
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
        this.state.value = LoginViewState.ShowLoading(false)
        this.state.value = LoginViewState.OnError(Exception("Error"))
    }

    fun loadPublicKey(uid: String) {

        this.state.value = LoginViewState.ShowLoading(false)

        getPublicKeyCase.execute(

            onResponse = { response ->
                if (response != null) {
                    response.publicKey?.let { getAccessToken(uid, it) }
                } else {
                    onError()
                }
            },

            onError = { onError() }
        )
    }

    private fun getAccessToken(uid: String, publicKey: PublicKey) {

        getAccessTokenUseCase.request = GetAccessTokenRequest(uid, publicKey.publicKey)

        getAccessTokenUseCase.execute(

            onResponse = { response ->
                login(response?.accessToken?.value)
                this.state.value = LoginViewState.ShowLoading(true)
            },

            onError = { onError() }
        )
    }

    private fun login(token: String?) {

        callLoginUseCase.request = CallLoginRequest(user?.value, token)

        callLoginUseCase.execute(

            onResponse = {
                this.event.value = LoginViewEvent.OnReceiveSessionToken(it?.sessionToken)
                this.state.value = LoginViewState.HideLoading(true)
            },

            onError = { onError() }
        )
    }

}