package br.com.aleson.daily.rewards.app.core.ui

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.core.exception.BuildImplementationException
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BaseBottomSheetDialog<T> : BottomSheetDialogFragment() {

    private var mBehavior: BottomSheetBehavior<View>? = null
    private var dialog: BottomSheetDialog? = null
    private var hasFullScreen = false

    private lateinit var callBack: DialogFragmentCallBack<T>
    private lateinit var holder: RecyclerView.ViewHolder

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        val view = holder.itemView as ConstraintLayout

        if (hasFullScreen) {
            val params = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            params.height = screenHeight
            view.layoutParams = params
        }
        dialog?.setContentView(view)
        mBehavior =
            BottomSheetBehavior.from(view.parent as View)
        mBehavior?.isHideable = false
        return dialog as BottomSheetDialog
    }

    override fun onStart() {
        super.onStart()
        mBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    class Builder<T> {
        private val bottomDialogFragment: BaseBottomSheetDialog<T> = BaseBottomSheetDialog()

        fun fullScreen(enable: Boolean): Builder<T> {
            bottomDialogFragment.hasFullScreen = enable
            return this
        }

        fun touchOutside(enable: Boolean): Builder<T> {
            bottomDialogFragment.isCancelable = enable
            return this
        }

        fun viewHolder(callBack: DialogFragmentCallBack<T>): Builder<T>? {
            bottomDialogFragment.holder = callBack.setViewHolder() as RecyclerView.ViewHolder
            bottomDialogFragment.callBack = callBack
            return this
        }

        fun build(): BaseBottomSheetDialog<T> {
            if (bottomDialogFragment.holder == null) {
                throw BuildImplementationException("Holder instance needed")
            }
            return bottomDialogFragment
        }

    }

    interface DialogFragmentCallBack<T> {
        fun setViewHolder(): T
        fun onBindData(holder: T)
    }

    companion object {
        val screenHeight: Int get() = Resources.getSystem().displayMetrics.heightPixels
    }
}