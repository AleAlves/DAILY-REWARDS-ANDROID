package br.com.aleson.daily.rewards.app.feature.home.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.GenericBinder
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view), GenericBinder<Tasks> {

    private val name: TextView = view.findViewById(R.id.name)

    override fun bind(data: Tasks, listener: BaseRecyclerListener<Tasks>?) {
        name.text = data.title
    }
}