package br.com.aleson.daily.rewards.app.core.ui

import android.content.Context
import android.view.View
import androidx.viewpager2.widget.ViewPager2


class ViewPagerStack(var context: Context) : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width
        val pageHeight = view.height

        when {
            position <= -1f -> {
                view.alpha = 0f
            }
            position > -1f  -> {
                view.alpha = 1f - position
            }
            position < 1f  -> {
                view.alpha = 1f - position
            }
            position <= 1 -> {
                view.alpha = 0f
            }
        }

        if (-1 < position && position < 0) {
            val scaleFactor = 1 - Math.abs(position) * 0.1f
            val verticalMargin = pageHeight * (1 - scaleFactor) / 2
            val horizontalMargin = pageWidth * (1 - scaleFactor) / 2
            if (position < 0) {
                view.translationX = horizontalMargin - verticalMargin / 2
            } else {
                view.translationX = -horizontalMargin + verticalMargin / 2
            }
            view.scaleX = scaleFactor
            view.scaleY = scaleFactor
        }

        view.translationX = view.width * -position

        if (position > 0) {
            val yPosition = position * view.height
            view.translationY = yPosition
        }
    }
}