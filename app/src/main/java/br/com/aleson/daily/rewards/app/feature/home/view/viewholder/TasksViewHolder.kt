package br.com.aleson.daily.rewards.app.feature.home.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseAdapterItem
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.GenericBinder
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view),
    GenericBinder<BaseAdapterItem<Tasks>> {

    private val name: TextView = view.findViewById(R.id.name)

    override fun bind(
        data: BaseAdapterItem<Tasks>,
        listener: BaseRecyclerListener<BaseAdapterItem<Tasks>>?
    ) {
        name.text = data.item?.title
    }
}

class TasksOptionViewHolder(view: View) : RecyclerView.ViewHolder(view),
    GenericBinder<BaseAdapterItem<Tasks>> {

    private val container: ConstraintLayout =
        view.findViewById(R.id.tasks_constraintlayout_container)

    override fun bind(
        data: BaseAdapterItem<Tasks>,
        listener: BaseRecyclerListener<BaseAdapterItem<Tasks>>?
    ) {
        container.setOnClickListener { }
    }
}