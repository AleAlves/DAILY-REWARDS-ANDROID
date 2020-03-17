package br.com.aleson.daily.rewards.app.core.ui

import androidx.viewpager2.widget.ViewPager2

class ViewPager2PageChangeCallback(private val listener: (Int) -> Unit) :
    ViewPager2.OnPageChangeCallback() {

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        when (position) {
            1 -> listener.invoke(3)
            3 -> listener.invoke(1)
        }
    }
}