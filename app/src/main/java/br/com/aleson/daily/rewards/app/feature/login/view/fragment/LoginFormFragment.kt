package br.com.aleson.daily.rewards.app.feature.login.view.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseFragment
import br.com.aleson.daily.rewards.app.core.firebase.FirebaseAuthHelper
import br.com.aleson.daily.rewards.app.core.firebase.RC_SIGN_IN
import br.com.aleson.daily.rewards.app.feature.home.view.HomeActivity
import br.com.aleson.daily.rewards.app.feature.login.di.LoginInjector
import br.com.aleson.daily.rewards.app.feature.login.view.viewstate.LoginViewEvent
import br.com.aleson.daily.rewards.app.feature.login.view.viewstate.LoginViewState
import br.com.aleson.daily.rewards.app.feature.login.viewmodel.LoginViewModel
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseUser


class LoginFormFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var textiviewVersion: TextView
    private lateinit var firebaseAuthHelper: FirebaseAuthHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }

    override fun onBindView(view: View) {

        this.textiviewVersion = view.findViewById(R.id.textview_version)

        view.findViewById<SignInButton>(R.id.login_frag_button_signin).setOnClickListener {
            signIn()
        }
    }

    override fun setupView() {
        this.firebaseAuthHelper = FirebaseAuthHelper(context)
        this.firebaseAuthHelper.iniGoogleSignInClient(context)
        this.varifyLogin()
    }

    override fun setupViewModel() {

        this.viewModel = ViewModelProviders.of(this, activity?.baseContext?.let {
            LoginInjector.provideLoginViewModelFactory()
        }).get(LoginViewModel::class.java)

        this.viewModel.setup()
    }


    override fun oberserverStates() {

        this.viewModel?.viewState?.observe(this, Observer {
            when (it) {
                is LoginViewState.ShowLoading -> super.showLoading()
                is LoginViewState.HideLoading -> super.hideLoading()
                is LoginViewState.OnError -> super.showToast(context, "Error")
            }
        })
    }

    override fun oberserverEvent() {

        this.viewModel?.viewEvent?.observe(this, Observer {
            when (it) {
                is LoginViewEvent.OnReceiveSessionToken -> navigateHome()
            }
        })
    }

    override fun getFragmentTag(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFragmentLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun varifyLogin() {
        if (this.firebaseAuthHelper.isLoggedIn()) {
            this.viewModel?.user?.value = firebaseAuthHelper.user()
            viewModel?.loadPublicKey(this.firebaseAuthHelper.auth()?.uid!!)
        }
    }

    private fun signIn() {
        startActivityForResult(firebaseAuthHelper.signInClient()?.signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            firebaseAuthHelper.authWithGoogle(
                activity as Activity,
                data,
                onSuccess = { user ->
                    login(user)
                },
                onFail = {
                    super.showToast(context, "falhou")
                }
            )
        }
    }

    private fun login(user: FirebaseUser?) {
        this.viewModel?.user?.value = firebaseAuthHelper.user()
        user?.uid?.let { this.viewModel?.loadPublicKey(it) }
    }

    private fun navigateHome() {
        startActivity(Intent(activity, HomeActivity::class.java))
    }

}
