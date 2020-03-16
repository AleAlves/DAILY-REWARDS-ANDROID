package br.com.aleson.daily.rewards.app.core.ui

import android.content.Context
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs


class ViewPagerStack(var context: Context) : ViewPager2.PageTransformer {

    private val MIN_SCALE = 0.9f

    override fun transformPage(page: View, position: Float) {

        page.apply {
            if (position >= 0) {
                page.scaleX = 0.9f - 0.02f * position
                page.scaleY = 0.9f
                page.translationX = -page.width * position
                page.translationY = -10 * position
            } else {
                page.scaleX = 0.9f + 0.02f * position
                page.scaleY = 0.9f
                page.translationX = page.width * position
                page.translationY = 10 * position
            }
        }
        when {
            position < -1 -> page.alpha = 0.1f
            position <= 1 -> {
                page.alpha = 0.2f.coerceAtLeast(1 - abs(position))
            }
            else -> page.alpha = 0.8f
        }

    }
}