package br.com.aleson.daily.rewards.app.core.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun init()

    abstract fun onBindView(view: View)

    abstract fun setupViewModel()

    abstract fun getFragmentTag(): String

    abstract fun onBackPressed(): Unit

    abstract fun oberserverStates()

    abstract fun oberserverEvent()

    protected abstract fun getFragmentLayout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupViewModel()
        this.init()
        this.onBindView(view)
        this.oberserverEvent()
        this.oberserverStates()
    }

    fun showToast(context: Context?, string: String){
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }

    fun showLoading() {

    }

    fun hideLoading() {

    }

}