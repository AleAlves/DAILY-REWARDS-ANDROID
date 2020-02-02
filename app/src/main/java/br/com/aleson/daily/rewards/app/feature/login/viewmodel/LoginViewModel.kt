package br.com.aleson.daily.rewards.app.feature.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.daily.rewards.app.core.base.BaseViewModel
import br.com.aleson.daily.rewards.app.feature.login.model.SessionToken
import br.com.aleson.daily.rewards.app.feature.login.usecase.AccessTokenUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.LoginUseCase
import br.com.aleson.daily.rewards.app.feature.login.usecase.PublicKeyUseCase
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(
    private val publicKeyCase: PublicKeyUseCase,
    private val accessTokenUseCase: AccessTokenUseCase,
    private val loginUseCase: LoginUseCase

) : BaseViewModel() {

    lateinit var publicKey: MutableLiveData<PublicKey>
    lateinit var sesssionToken: MutableLiveData<SessionToken>
    var user: MutableLiveData<FirebaseUser>? = MutableLiveData()

    override fun init() {
        this.publicKey = MutableLiveData()
        this.sesssionToken = MutableLiveData()
    }

    override fun onError() {
        Log.e("", "")
    }

    fun loadPublicKey(uid: String) {
        publicKeyCase.getPublicKey(
            onResponse = { publicKey ->
                this.publicKey.value = publicKey
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
                val token = accessToken?.accessToken!!
                login(token)
            },
            onError = {

            })
    }

    private fun login(token: String) {
        loginUseCase.login(
            token,
            user?.value,
            onResponse = {
                sesssionToken?.value = it
            }, onError = {

            })
    }

}