package br.com.aleson.daily.rewards.app.core.ui

import android.content.Context
import android.util.Log
import android.view.View
import androidx.cardview.widget.CardView
import androidx.viewpager2.widget.ViewPager2
import br.com.aleson.daily.rewards.app.R
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor


class ViewPagerStack(var context: Context) : ViewPager2.PageTransformer {

    private val DEFAULT_SCALE = 1f
    private var dimen = 0
    private val cardElevationDefault = 10f
    private val leftToRight = true
    private val offsetXDps = 2.5f
    private val offsetYDps = 15f
    private var offsetXPxs = offsetXDps * context.resources.displayMetrics.density
    private var offsetYDxs = offsetYDps * context.resources.displayMetrics.density
    /**
     * Default angle of rotation during swipe
     */
    val DEFAULT_SWIPE_ROTATION = 30f
    /**
     * Angle of rotation of page during swipe
     */
    private val mRotateDegrees = DEFAULT_SWIPE_ROTATION

    override fun transformPage(view: View, position: Float) {
        dimen = view.width
        view.isFocusable = false
        if (true) {

            val transX = position
            val transY = -dimen * position + offsetYDxs * position

            Log.d("STACK", position.toString())

            if (position < 0) {
                if (position == -1f) {
                    view.alpha = 0f
                    view.scaleX = 1.9f
                    view.scaleY = 1.9f
                    view.background = context.getDrawable(R.color.color1)
                }
            } else if (position == 0f) {
                view.isFocusable = true
                view.alpha = 1.0f
                (view as CardView).cardElevation = cardElevationDefault;
                view.translationX = transX
                view.translationY = transY
                view.scaleX = 0.9f
                view.scaleY = 0.9f
                view.rotation = 0f
                view.background = context.getDrawable(R.color.color2)
            } else if (position > 0) {
                val ceil = round(ceil(abs(position).toDouble()).toFloat(), 1).toFloat()
                val floor = round(floor(abs(position).toDouble()).toFloat(), 1).toFloat()
                var newFloor = 0f
                var scale = 0f
                newFloor =
                    if (position == ceil && position == floor) {
                        round(((floor - 1) + 0.1).toFloat(), 1).toFloat()
                    } else round((floor + 0.1).toFloat(), 1).toFloat()
                val scaleTolerance = round(ceil / 10, 1).toFloat()
                val higher = round(DEFAULT_SCALE - scaleTolerance, 1).toFloat()
                val lower = round(higher - 0.1f, 1).toFloat()
                scale =
                    (abs(position) - abs(newFloor)) * (lower - higher) / (abs(ceil) - abs(newFloor)) + higher
                (view as CardView).cardElevation = cardElevationDefault - ceil
                val adjustedScale = if (scale <= higher) scale else higher
                view.scaleX = adjustedScale
                view.scaleY = if (scale <= higher) scale else higher
                view.translationX = transX
                view.translationY = transY
                view.background = context.getDrawable(R.color.color3)
                if (position < 5) {
                    view.alpha = 1.0f
                } else {
                    view.alpha = 0f
                }
            }
        } else {

        }
    }

    private fun round(value: Float, precision: Int): Double {
        val scale = Math.pow(10.0, precision.toDouble()).toInt()
        return (Math.round(value * scale).toFloat() / scale).toDouble()
    }
}