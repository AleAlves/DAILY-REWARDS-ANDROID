package br.com.aleson.daily.rewards.app.core.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.aleson.daily.rewards.app.feature.home.view.fragment.ActivitiesFragment


class ViewPagerFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val arrayList: ArrayList<Fragment> = ArrayList()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ActivitiesFragment ()
            1 -> ActivitiesFragment()
            2 -> ActivitiesFragment()
            else -> ActivitiesFragment()
        }
    }

    fun add(fragment: Fragment) {
        this.arrayList.add(fragment)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}