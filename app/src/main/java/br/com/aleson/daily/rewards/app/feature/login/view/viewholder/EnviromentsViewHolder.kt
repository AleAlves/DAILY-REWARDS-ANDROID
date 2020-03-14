package br.com.aleson.daily.rewards.app.feature.login.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.model.Enviroment
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.GenericBinder

class EnviromentsViewHolder(view: View) : RecyclerView.ViewHolder(view), GenericBinder<Enviroment> {

    private val recyclerView: RecyclerView = view.findViewById(R.id.enviroments_recyclerview)

    override fun bind(data: Enviroment, listener: BaseRecyclerListener<Enviroment>?) {
        recyclerView.adapter
    }
}