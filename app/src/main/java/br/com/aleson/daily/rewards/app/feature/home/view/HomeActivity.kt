package br.com.aleson.daily.rewards.app.feature.home.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerViewAdapter
import br.com.aleson.daily.rewards.app.feature.home.view.viewholder.TasksViewHolder
import br.com.aleson.daily.rewards.app.feature.login.model.User

class HomeActivity : AppCompatActivity(), BaseRecyclerListener<User> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    private val adapter = object : BaseRecyclerViewAdapter<User>(this) {

        override fun getViewHolder(
            view: View,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return TasksViewHolder(
                view
            )
        }

        override fun getLayoutId(position: Int, obj: User): Int {
            return R.layout.user_list
        }
    }

    override fun onClickListener(data: User, v: View, code: Int) {
    }
}
