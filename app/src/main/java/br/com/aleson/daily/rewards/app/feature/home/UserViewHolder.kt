package br.com.aleson.daily.rewards.app.feature.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.GenericBinder
import br.com.aleson.daily.rewards.app.feature.login.model.User

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view), GenericBinder<User> {

    private val name: TextView = view.findViewById(R.id.name)

    override fun bind(data: User, listener: BaseRecyclerListener<User>?) {
        name.text = data.name
    }
}