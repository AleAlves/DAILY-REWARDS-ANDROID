package br.com.aleson.daily.rewards.app.core.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun getFragmentTag(): String

    abstract fun onBackPressed(): Unit

    protected abstract fun getFragmentLayout(): Int

    fun showToast(context: Context?, string: String){
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }

}