package br.com.aleson.daily.rewards.app.feature.login.repository

import br.com.aleson.core.tools.coretools.CoreToolsBuilder
import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPRequest
import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.core.repository.BaseRepository
import br.com.aleson.daily.rewards.app.core.session.Session
import br.com.aleson.daily.rewards.app.feature.login.model.AccessToken
import br.com.aleson.daily.rewards.app.feature.login.model.SessionToken
import br.com.aleson.daily.rewards.app.feature.login.model.User
import br.com.aleson.daily.rewards.app.feature.login.model.UserKeyChain
import br.com.aleson.daily.rewards.app.feature.login.repository.data.LoginRemoteDataSource
import br.com.aleson.daily.rewards.app.feature.login.repository.service.LoginServices
import br.com.aleson.daily.rewards.app.feature.login.usecase.CallLoginResponse
import br.com.aleson.daily.rewards.app.feature.login.usecase.GetAccessTokenResponse
import br.com.aleson.daily.rewards.app.feature.login.usecase.GetPublicKeyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(
    private val loginServices: LoginServices?,
    private val core: CoreToolsBuilder
) : BaseRepository(), LoginRemoteDataSource {

    override fun requestPublicKeyCallback(onResponse: (GetPublicKeyResponse?) -> Unit, onError: () -> Unit) {

        val callback = object : Callback<HTTPResponse<PublicKey>> {
            override fun onFailure(
                call: Call<HTTPResponse<PublicKey>>,
                t: Throwable
            ) {
                onError()
            }

            override fun onResponse(
                call: Call<HTTPResponse<PublicKey>>,
                response: Response<HTTPResponse<PublicKey>>
            ) {
                onResponse(GetPublicKeyResponse(response.body()?.data as PublicKey))
            }
        }

        loginServices?.getPublicKey()?.enqueue(callback)
    }

    override fun requestAccessToken(
        firebaseToken: String,
        publicKey: String,
        onResponse: (GetAccessTokenResponse?) -> Unit,
        onError: () -> Unit
    ) {
        val callback = object : Callback<HTTPResponse<AccessToken>> {
            override fun onFailure(
                call: Call<HTTPResponse<AccessToken>>,
                t: Throwable
            ) {
                onError()
            }

            override fun onResponse(
                call: Call<HTTPResponse<AccessToken>>,
                response: Response<HTTPResponse<AccessToken>>
            ) {
                onResponse(GetAccessTokenResponse(response.body()?.data as AccessToken))
            }
        }

        val keyChain = UserKeyChain(
            core.crypto.AES().key(),
            core.crypto.AES().salt(),
            core.crypto.AES().iv()
        )

        val request = HTTPRequest(core.crypto.RSA(publicKey).encrypt(keyChain))

        loginServices?.getAccessToken(request)?.enqueue(callback)
    }

    override fun requestLogin(
        accessToken: String,
        user: User,
        onResponse: (CallLoginResponse?) -> Unit,
        onError: () -> Unit
    ) {

        val callback = object : Callback<HTTPResponse<SessionToken>> {
            override fun onFailure(
                call: Call<HTTPResponse<SessionToken>>,
                t: Throwable
            ) {
                onError()
            }

            override fun onResponse(
                call: Call<HTTPResponse<SessionToken>>,
                response: Response<HTTPResponse<SessionToken>>
            ) {
                Session.getInstance()?.setSessionToken(response.body()?.data as SessionToken)
                onResponse(CallLoginResponse(getSessionToken()))
            }
        }

        val httpRequest = HTTPRequest(core.crypto.AES().encrypt(user))

        loginServices?.login(accessToken, httpRequest)?.enqueue(callback)
    }
}