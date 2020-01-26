package br.com.aleson.daily.rewards.app.ui.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun getFragmentTag(): String

    abstract fun onBackPressed(): Unit

    protected abstract fun getFragmentLayout(): Int

}