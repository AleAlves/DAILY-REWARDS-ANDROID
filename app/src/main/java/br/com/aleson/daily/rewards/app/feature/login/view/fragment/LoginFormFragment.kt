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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseAdapterItem
import br.com.aleson.daily.rewards.app.core.base.BaseFragment
import br.com.aleson.daily.rewards.app.core.di.BaseProvider
import br.com.aleson.daily.rewards.app.core.firebase.FirebaseAuthHelper
import br.com.aleson.daily.rewards.app.core.firebase.RC_SIGN_IN
import br.com.aleson.daily.rewards.app.core.model.Enviroment
import br.com.aleson.daily.rewards.app.core.ui.BaseBottomSheetDialog
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerViewAdapter
import br.com.aleson.daily.rewards.app.feature.home.view.HomeActivity
import br.com.aleson.daily.rewards.app.feature.login.di.LoginInjector
import br.com.aleson.daily.rewards.app.feature.login.view.viewholder.EnviromentCustomViewHolder
import br.com.aleson.daily.rewards.app.feature.login.view.viewholder.EnviromentItemViewHolder
import br.com.aleson.daily.rewards.app.feature.login.view.viewholder.EnviromentsViewHolder
import br.com.aleson.daily.rewards.app.feature.login.view.viewstate.LoginViewEvent
import br.com.aleson.daily.rewards.app.feature.login.view.viewstate.LoginViewState
import br.com.aleson.daily.rewards.app.feature.login.viewmodel.LoginViewModel
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseUser


class LoginFormFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var textiviewVersion: TextView
    private lateinit var firebaseAuthHelper: FirebaseAuthHelper
    private lateinit var enviromentRecylerView: RecyclerView

    private var enviromentsBottomSheet: BaseBottomSheetDialog<EnviromentsViewHolder>? = null

    private var enviromentClickListener = object : BaseRecyclerListener<BaseAdapterItem<Enviroment>> {

        override fun onClickListener(data: BaseAdapterItem<Enviroment>, v: View) {
            BaseProvider.setServerUrl(data.item?.url.toString())
            varifyLogin()
            enviromentsBottomSheet?.dismiss()
        }
    }

    private var enviromentAdapter =
        object : BaseRecyclerViewAdapter<BaseAdapterItem<Enviroment>>(enviromentClickListener) {

            override fun getLayoutId(position: Int, obj: BaseAdapterItem<Enviroment>): Int {
                if (obj.option.isEmpty()) {
                    return R.layout.enviroment_item_holder
                }
                return R.layout.enviroment_item_custom_holder

            }

        override fun getViewHolder(
            view: View,
            viewType: Int
        ): RecyclerView.ViewHolder {
            if (R.layout.enviroment_item_custom_holder == viewType) {
                return EnviromentCustomViewHolder(view)
            }
            return EnviromentItemViewHolder(view)
        }

    }

    private val enviromentViewHolder =
        object : BaseBottomSheetDialog.ViewHolder<EnviromentsViewHolder> {

            override fun holderLayout(): Int {
                return R.layout.enviroments_holder
            }

            override fun setViewHolder(): EnviromentsViewHolder? {
                val itemView = View.inflate(context, holderLayout(), null)
                return itemView?.let { EnviromentsViewHolder(it) }
            }

            override fun onBindData(holder: EnviromentsViewHolder) {
                enviromentRecylerView = holder.itemView.findViewById(R.id.enviroments_recyclerview) as RecyclerView
                enviromentRecylerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                enviromentRecylerView.adapter = enviromentAdapter
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }

    override fun onBindView(view: View) {

        this.textiviewVersion = view.findViewById(R.id.textview_version)

        view.findViewById<SignInButton>(R.id.login_frag_button_signin).setOnClickListener { signIn() }
    }

    override fun setupView() {
        this.firebaseAuthHelper = FirebaseAuthHelper(context)

        this.firebaseAuthHelper.iniGoogleSignInClient(context)

        this.enviromentsBottomSheet = BaseBottomSheetDialog.Builder<EnviromentsViewHolder>()
            .fullScreen(false)
            .touchOutside(false)
            .viewHolder(enviromentViewHolder)
            ?.build()

        var dev = true
        if (dev) {
            this.getEnviroments()
        } else {
            varifyLogin()
        }

    }

    private fun showEnviromentChooser() {
        this.fragmentManager?.let { this.enviromentsBottomSheet?.show(it, this.tag) }
    }

    override fun setupViewModel() {

        this.viewModel = ViewModelProviders.of(this, activity?.baseContext?.let {
            context?.let { it1 -> LoginInjector.provideLoginViewModelFactory(it1) }
        }).get(LoginViewModel::class.java)

        this.viewModel.setup()
    }


    override fun oberserverStates() {

        this.viewModel.viewState.observe(this, Observer {
            when (it) {
                is LoginViewState.ShowLoading -> super.showLoading()
                is LoginViewState.HideLoading -> super.hideLoading()
                is LoginViewState.OnError -> super.showToast(context, "Error")
            }
        })
    }

    override fun oberserverEvent() {

        this.viewModel.viewEvent.observe(this, Observer {
            when (it) {
                is LoginViewEvent.OnReceiveSessionToken -> navigateHome()
                is LoginViewEvent.OnLoadEnviroments -> loadEnviroments(it.enviroments)
            }
        })
    }

    private fun loadEnviroments(enviroments: List<Enviroment>?) {
        enviroments?.forEach {
            enviromentAdapter.add(BaseAdapterItem(it))
        }
        enviromentAdapter.add(BaseAdapterItem(option = "Custom"))
        this.showEnviromentChooser()
    }

    override fun getFragmentTag(): String {
        return this.tag.toString()
    }

    override fun onBackPressed() {}

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_login_form
    }

    private fun getEnviroments() {
        viewModel.getEnviroments()
    }

    private fun varifyLogin() {
        if (this.firebaseAuthHelper.isLoggedIn()) {
            this.viewModel.user?.value = firebaseAuthHelper.user()
            viewModel.loadPublicKey(this.firebaseAuthHelper.auth()?.uid.toString())
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
        this.viewModel.user?.value = firebaseAuthHelper.user()
        user?.uid?.let { this.viewModel.loadPublicKey(it) }
    }

    private fun navigateHome() {
        startActivity(Intent(activity, HomeActivity::class.java))
    }

}
