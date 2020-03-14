package br.com.aleson.daily.rewards.app.core.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import java.lang.Exception

abstract class BaseFragment : BaseDialogFragment() {

    private var dialog : Dialog? = null

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
        if(dialog == null){
            dialog = super.loading(context as Context) as Dialog
        }
        if (dialog?.isShowing as Boolean){
            throw Exception("Can't show more than one loading")
        }
        else {
            dialog?.show()
        }
    }

    fun hideLoading() {
        if (dialog?.isShowing as Boolean)
            dialog?.dismiss()
    }

}