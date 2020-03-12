package br.com.aleson.daily.rewards.app.feature.home.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import br.com.aleson.daily.rewards.app.core.ui.StackScroller
import java.lang.ref.WeakReference

class StackViewPager(context: Context, attrs: AttributeSet) : ViewPager2(context, attrs) {

    val SwipeDirection_RIGHT = 1
    val SwipeDirection_LEFT = 2
    val SwipeDirection_ALL = 0
    val SwipeDirection_NONE = -1
    val SCROLL_MODE_DEFAULT = 250
    val SCROLL_MODE_MEDIUM = 750
    val SCROLL_MODE_SLOW = 1000
    val SCROLL_MODE_ULTRA_SLOW = 2000

    private var initialXValue = 0f
    private var direction = SwipeDirection_ALL
    private var ownScroller: StackScroller? = null

    fun setScrollDuration(millis: Int) {
        try {
            val viewpager: Class<*> = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            val wr =
                WeakReference(context)
            ownScroller = StackScroller(wr.get()!!, millis)
            scroller[this] = ownScroller
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (IsSwipeAllowed(event)) {
            super.onTouchEvent(event)
        } else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (IsSwipeAllowed(event)) {
            try {
                super.onInterceptTouchEvent(event)
            } catch (e: Exception) {
                false
            }
        } else false
    }

    private fun IsSwipeAllowed(event: MotionEvent): Boolean {
        if (direction == SwipeDirection_ALL) return true
        if (direction == SwipeDirection_NONE) //disable any swipe
            return false
        if (event.action == MotionEvent.ACTION_DOWN) {
            initialXValue = event.x
            return true
        }
        if (event.action == MotionEvent.ACTION_MOVE) {
            try {
                val diffX = event.x - initialXValue
                if (diffX > 0 && direction == SwipeDirection_RIGHT) { // swipe from left to right detected
                    return false
                } else if (diffX < 0 && direction == SwipeDirection_LEFT) { // swipe from right to left detected
                    return false
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
        return true
    }

    fun setSwipeDirection(direction: Int) {
        this.direction = direction
    }

}