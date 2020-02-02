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
import br.com.aleson.core.tools.coretools.cryptography.model.PublicKey
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseFragment
import br.com.aleson.daily.rewards.app.core.firebase.FirebaseAuthHelper
import br.com.aleson.daily.rewards.app.core.firebase.RC_SIGN_IN
import br.com.aleson.daily.rewards.app.feature.login.di.Injector
import br.com.aleson.daily.rewards.app.feature.login.viewmodel.LoginViewModel
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseUser


class LoginFormFragment : BaseFragment() {

    private lateinit var textiviewVersion: TextView
    private var viewModel: LoginViewModel? = null
    private lateinit var firebaseAuthHelper: FirebaseAuthHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuthHelper = FirebaseAuthHelper(context)

        textiviewVersion = view.findViewById(R.id.textview_version)

        viewModel = ViewModelProviders.of(this,
            activity?.baseContext?.let { Injector.provideLoginViewModelFactory() })
            .get(LoginViewModel::class.java)

        firebaseAuthHelper.iniGoogleSignInClient(context)

        viewModel?.init()

        viewModel?.publicKey?.observe(this, Observer<PublicKey> { response ->
            this.textiviewVersion.text = response.publicKey
        })

        viewModel?.sesssionToken?.observe(this, Observer {
            showToast(context, "login")
        })

        view.findViewById<SignInButton>(R.id.login_frag_button_signin).setOnClickListener {
            signIn()
        }

        if (firebaseAuthHelper.isLoggedIn()) {
            viewModel?.user?.value = firebaseAuthHelper.user()
            firebaseAuthHelper.auth()?.uid?.let { viewModel?.loadPublicKey(it) }
        }

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

    private fun login(user: FirebaseUser) {
        viewModel?.user?.value = firebaseAuthHelper.user()
        viewModel?.loadPublicKey(user.uid)
    }

}
