package br.com.aleson.daily.rewards.app.feature.home.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseFragment
import br.com.aleson.daily.rewards.app.core.ui.*
import br.com.aleson.daily.rewards.app.feature.home.di.injector.HomeInjector
import br.com.aleson.daily.rewards.app.feature.home.model.Group
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks
import br.com.aleson.daily.rewards.app.feature.home.view.viewholder.GroupsViewHolder
import br.com.aleson.daily.rewards.app.feature.home.view.viewholder.TasksViewHolder
import br.com.aleson.daily.rewards.app.feature.home.viewmodel.HomeViewModel


class ActivitiesFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var tasksRecylerView: RecyclerView
    private lateinit var gropuRecylerView: RecyclerView
    private lateinit var viewPager: ViewPager2
    private lateinit var stackPagerAdapter: StackPagerAdapter

    private var tasksClickListener = object : BaseRecyclerListener<Tasks> {

        override fun onClickListener(data: Tasks, v: View) {

        }
    }

    private var groupClickListener = object : BaseRecyclerListener<Group> {

        override fun onClickListener(data: Group, v: View) {

        }
    }

    private var tasksAdapter = object : BaseRecyclerViewAdapter<Tasks>(tasksClickListener) {

        override fun getViewHolder(
            view: View,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return TasksViewHolder(view)
        }

        override fun getLayoutId(position: Int, obj: Tasks): Int {
            return R.layout.tasks_holder
        }
    }

    private var gorupsAdapter = object : BaseRecyclerViewAdapter<Group>(groupClickListener) {

        override fun getViewHolder(
            view: View,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return GroupsViewHolder(view)
        }

        override fun getLayoutId(position: Int, obj: Group): Int {
            return R.layout.tasks_holder
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_acitvities, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onBindView(view: View) {
        tasksRecylerView = view.findViewById(R.id.recycler_view_tasks)
        gropuRecylerView = view.findViewById(R.id.recycler_view_groups)
        viewPager = view.findViewById(R.id.view_pager2)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setupView() {
        val layoutMutableList = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        tasksRecylerView.addItemDecoration(OverLapDecoration())
        tasksRecylerView.layoutManager = layoutMutableList

        tasksRecylerView.adapter = tasksAdapter
        gropuRecylerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        gropuRecylerView.adapter = gorupsAdapter

        viewPager.setPageTransformer(context?.let { ViewPagerStack(it) })
        viewPager.overScrollMode = View.OVER_SCROLL_NEVER
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        viewPager.adapter = tasksAdapter
    }

    override fun setupViewModel() {
        this.viewModel = ViewModelProviders.of(this, activity?.baseContext?.let {
            HomeInjector.provideHomeViewModelFactory()
        }).get(HomeViewModel::class.java)

        viewModel.getTasks()
        viewModel.getGroups()
    }

    override fun getFragmentTag(): String {
        return this.tag.toString()
    }

    override fun onBackPressed() {
    }

    override fun oberserverStates() {
    }

    override fun oberserverEvent() {

        viewModel.taskslist?.observe(this, Observer {
            tasksAdapter.add(it)
        })

        viewModel.groupslist?.observe(this, Observer {
            gorupsAdapter.add(it)
        })
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
