package br.com.aleson.daily.rewards.app.core.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast

abstract class BaseFragment : BaseDialogFragment() {

    abstract fun onBindView(view: View)

    abstract fun setupView()

    abstract fun setupViewModel()

    abstract fun getFragmentTag(): String

    abstract fun onBackPressed(): Unit

    abstract fun oberserverStates()

    abstract fun oberserverEvent()

    protected abstract fun getFragmentLayout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupViewModel()
        this.onBindView(view)
        this.setupView()
        this.oberserverEvent()
        this.oberserverStates()
    }

    fun showToast(context: Context?, string: String){
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }

    fun showLoading() {
        val dialog = super.loading(context!!)
        dialog?.show()
    }

    fun hideLoading() {

    }

}