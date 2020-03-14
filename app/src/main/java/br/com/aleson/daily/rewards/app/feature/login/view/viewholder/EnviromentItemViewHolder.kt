package br.com.aleson.daily.rewards.app.feature.login.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseAdapterItem
import br.com.aleson.daily.rewards.app.core.model.Enviroment
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.GenericBinder

class EnviromentItemViewHolder(view: View) : RecyclerView.ViewHolder(view), GenericBinder<BaseAdapterItem<Enviroment>> {

    private val url: TextView = view.findViewById(R.id.enviroment_holder_url)
    private val name: TextView = view.findViewById(R.id.enviroment_holder_name)
    private val description: TextView = view.findViewById(R.id.enviroment_holder_description)
    private val container: ConstraintLayout = view.findViewById(R.id.enviroment_holder_container)

    override fun bind(data: BaseAdapterItem<Enviroment>, listener: BaseRecyclerListener<BaseAdapterItem<Enviroment>>?) {
        name.text = data.item?.name
        url.text = data.item?.url
        description.text = data.item?.description
        container.setOnClickListener {
            listener?.onClickListener(data, it)
        }
    }
}