package br.com.aleson.daily.rewards.app.feature.home.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.GenericBinder
import br.com.aleson.daily.rewards.app.feature.home.model.Group

class GroupsViewHolder(view: View) : RecyclerView.ViewHolder(view), GenericBinder<Group> {

    private val name: TextView = view.findViewById(R.id.name)


    override fun bind(data: Group, listener: BaseRecyclerListener<Group>?) {
        name.text = data.title
    }
}