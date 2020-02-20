package br.com.aleson.daily.rewards.app.feature.home.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseFragment
import br.com.aleson.daily.rewards.app.feature.home.di.injector.HomeInjector
import br.com.aleson.daily.rewards.app.feature.home.viewmodel.HomeViewModel


class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onBindView(view: View) {
    }

    override fun setupView() {
    }

    override fun setupViewModel() {
        this.viewModel = ViewModelProviders.of(this, activity?.baseContext?.let {
            HomeInjector.provideHomeViewModelFactory()
        }).get(HomeViewModel::class.java)

        viewModel.setup()
    }

    override fun getFragmentTag(): String {
        return this.tag.toString()
    }

    override fun onBackPressed() {
    }

    override fun oberserverStates() {
    }

    override fun oberserverEvent() {
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

}
