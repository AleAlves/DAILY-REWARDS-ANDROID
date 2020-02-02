package br.com.aleson.daily.rewards.app.feature.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.daily.rewards.app.core.base.BaseViewModel
import br.com.aleson.daily.rewards.app.feature.login.usecase.PublicKeyUseCase
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(
    private val publicKeyCase: PublicKeyUseCase

) : BaseViewModel() {

    lateinit var publicKey: MutableLiveData<PublicKey>

    override fun init() {
        this.publicKey = MutableLiveData()
    }

    override fun onError() {
        Log.e("", "")
    }

    fun loadPublicKey() {
        publicKeyCase.getPublicKey(
            onResponse = { publicKey -> this.publicKey.value = publicKey },
            onError = { onError() })
    }

}