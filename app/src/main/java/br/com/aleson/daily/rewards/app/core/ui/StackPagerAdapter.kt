package br.com.aleson.daily.rewards.app.core.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks


class StackPagerAdapter(private val context: Context, private var tasks: List<Tasks>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.tasks_holder, container, false)
        val textview = view.findViewById<TextView>(R.id.name)
        textview.text = tasks[position].title
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return tasks.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView)
    }

}