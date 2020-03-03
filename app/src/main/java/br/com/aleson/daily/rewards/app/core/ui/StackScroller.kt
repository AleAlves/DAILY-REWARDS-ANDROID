package br.com.aleson.daily.rewards.app.core.ui

import android.content.Context
import android.widget.Scroller

class StackScroller(context: Context, durationScrollMillis: Int) : Scroller(context) {
    private var durationScrollMillis = 1

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, durationScrollMillis)
    }
}