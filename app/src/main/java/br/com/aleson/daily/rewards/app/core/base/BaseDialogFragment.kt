package br.com.aleson.daily.rewards.app.core.base

import android.app.Dialog
import android.content.Context
import androidx.fragment.app.Fragment
import br.com.aleson.daily.rewards.app.R

open class BaseDialogFragment : Fragment() {

    private var dialog: Dialog? = null

    fun loading(context: Context): Dialog? {
        dialog = Dialog(context)
        dialog?.requestWindowFeature(1)
        dialog?.setContentView(R.layout.default_loading)
        dialog?.setCancelable(false)
        //dialog?.window?.setBackgroundDrawableResource(R.color.)
        return dialog
    }


}