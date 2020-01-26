package br.com.aleson.daily.rewards.app.presenter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.aleson.daily.rewards.app.data.usecase.UseCaseHandler
import br.com.aleson.daily.rewards.app.domain.security.PublicKey
import br.com.aleson.daily.rewards.app.domain.usecases.GetPublicKeyRequest
import br.com.aleson.daily.rewards.app.domain.usecases.PublicKeyUseCase
import br.com.aleson.daily.rewards.app.presenter.base.BaseViewModel

class LoginViewModel(
    useCaseHandler: UseCaseHandler,
    private val publicKeyCase: PublicKeyUseCase
) : BaseViewModel(useCaseHandler) {

    lateinit var publicKey: MutableLiveData<PublicKey>

    override fun init() {
        this.publicKey = MutableLiveData()
    }

    override fun onError() {
        Log.e("", "")
    }

    fun loadPublicKey() {
        val request = GetPublicKeyRequest()
        mUseCaseHandler?.executeUseCase(publicKeyCase, request,
            {
                this.publicKey.value = it.response?.data
            },
            {
                onError()
            }
        )
    }

}