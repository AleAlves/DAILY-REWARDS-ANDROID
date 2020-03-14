package br.com.aleson.daily.rewards.app.feature.login.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.model.Enviroment
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.GenericBinder

class EnviromentViewHolder(view: View) : RecyclerView.ViewHolder(view), GenericBinder<Enviroment> {

    private val url: TextView = view.findViewById(R.id.enviroment_holder_url)
    private val name: TextView = view.findViewById(R.id.enviroment_holder_name)
    private val description: TextView = view.findViewById(R.id.enviroment_holder_description)
    private val container: ConstraintLayout = view.findViewById(R.id.enviroment_holder_container)

    override fun bind(data: Enviroment, listener: BaseRecyclerListener<Enviroment>?) {
        name.text = data.name
        url.text = data.url
        description.text = data.description
        container.setOnClickListener {
            listener?.onClickListener(data, it)
        }
    }
}