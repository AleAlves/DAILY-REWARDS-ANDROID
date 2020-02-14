package br.com.aleson.daily.rewards.app.feature.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerViewAdapter
import br.com.aleson.daily.rewards.app.feature.login.model.User
import java.sql.Array

class HomeActivity : AppCompatActivity(), BaseRecyclerListener<User> {

    private lateinit var recyclerViewAdapter: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        recyclerViewAdapter = findViewById(R.id.recyclerview)
        recyclerViewAdapter.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter.adapter = adapter
        val mutableList = mutableListOf<User>()
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))
        mutableList.add(User("Aleson", "aleson", "aleson", "aleson"))

        adapter.add(mutableList)
    }

    private val adapter = object : BaseRecyclerViewAdapter<User>(this) {

        override fun getViewHolder(
            view: View,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return UserViewHolder(view)
        }

        override fun getLayoutId(position: Int, obj: User): Int {
            return R.layout.user_list
        }
    }

    override fun onClickListener(data: User, v: View, code: Int) {
    }
}
