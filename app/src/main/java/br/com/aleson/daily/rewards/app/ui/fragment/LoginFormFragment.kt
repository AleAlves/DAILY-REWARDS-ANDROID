package br.com.aleson.daily.rewards.app.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.domain.security.PublicKey
import br.com.aleson.daily.rewards.app.di.Injector
import br.com.aleson.daily.rewards.app.presenter.LoginViewModel
import br.com.aleson.daily.rewards.app.ui.base.BaseFragment


class LoginFormFragment : BaseFragment() {

    private lateinit var textiviewVersion: TextView
    private var viewModel: LoginViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textiviewVersion = view.findViewById(R.id.textview_version)

        viewModel = ViewModelProviders.of(this,
            activity?.baseContext?.let { Injector.provideLoginViewModelFactory() })
            .get(LoginViewModel::class.java)

        viewModel?.init()

        viewModel?.loadPublicKey()

        viewModel?.publicKey?.observe(this, Observer<PublicKey> { response ->
            this.textiviewVersion.text = response.publicKey
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
}
