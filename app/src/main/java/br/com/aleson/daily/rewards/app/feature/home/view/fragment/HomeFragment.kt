package br.com.aleson.daily.rewards.app.feature.home.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseFragment
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerViewAdapter
import br.com.aleson.daily.rewards.app.feature.home.di.injector.HomeInjector
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks
import br.com.aleson.daily.rewards.app.feature.home.view.viewholder.TasksViewHolder
import br.com.aleson.daily.rewards.app.feature.home.viewmodel.HomeViewModel


class HomeFragment : BaseFragment(), BaseRecyclerListener<Tasks> {

    private lateinit var viewModel: HomeViewModel
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var recylerView: RecyclerView

    private var adapter = object : BaseRecyclerViewAdapter<Tasks>(this) {

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onBindView(view: View) {
        recylerView = view.findViewById(R.id.recycler_view)
    }

    override fun setupView() {
        recylerView.layoutManager = LinearLayoutManager(context)
        recylerView.adapter = adapter
    }

    override fun setupViewModel() {
        this.viewModel = ViewModelProviders.of(this, activity?.baseContext?.let {
            HomeInjector.provideHomeViewModelFactory()
        }).get(HomeViewModel::class.java)

        viewModel.setup()
    }

    override fun getFragmentTag(): String {
        return this.tag.toString()
    }

    override fun onBackPressed() {
    }

    override fun oberserverStates() {
    }

    override fun oberserverEvent() {
        viewModel.list?.observe(this, Observer {
            adapter.add(it)
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

    override fun onClickListener(data: Tasks, v: View, code: Int) {
    }
}
