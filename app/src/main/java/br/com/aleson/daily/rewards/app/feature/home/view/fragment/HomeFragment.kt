package br.com.aleson.daily.rewards.app.feature.home.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseFragment
import br.com.aleson.daily.rewards.app.core.ui.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : BaseFragment() {

    var myViewPager2: ViewPager2? = null
    var myAdapter: ViewPagerFragmentAdapter? = null
    var tabLayout: TabLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onBindView(view: View) {
        tabLayout = view.findViewById(R.id.tab_layout)
        myViewPager2 = view.findViewById(R.id.home_viewpager)
        myAdapter = ViewPagerFragmentAdapter(this)

        myAdapter?.add(ActivitiesFragment())
        myAdapter?.add(ActivitiesFragment())
        myAdapter?.add(ActivitiesFragment())

        myViewPager2!!.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        myViewPager2!!.adapter = myAdapter

        TabLayoutMediator(tabLayout!!, myViewPager2!!,
            TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Todos"
                    }
                    1 -> {
                        tab.text = "Pessoais"
                    }
                    2 -> {
                        tab.text = "Grupos"
                    }
                }
            }).attach()
    }

    override fun setupView() {
    }


    override fun setupViewModel() {

    }

    override fun getFragmentTag(): String {
        return this.tag.toString()
    }

    override fun onBackPressed() {
    }

    override fun oberserverStates() {
    }

    override fun oberserverEvent() {

    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_home
    }

}
