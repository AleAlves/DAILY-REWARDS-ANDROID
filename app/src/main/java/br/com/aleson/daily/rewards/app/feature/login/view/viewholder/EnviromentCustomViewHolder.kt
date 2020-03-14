package br.com.aleson.daily.rewards.app.feature.login.view.viewholder

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseAdapterItem
import br.com.aleson.daily.rewards.app.core.model.Enviroment
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.core.ui.GenericBinder

class EnviromentCustomViewHolder(view: View) : RecyclerView.ViewHolder(view),
    GenericBinder<BaseAdapterItem<Enviroment>> {

    private val urlEditext: EditText = view.findViewById(R.id.enviroment_holder_url_editext)
    private val buttonConnect: TextView = view.findViewById(R.id.enviroment_holder_url_button)

    override fun bind(data: BaseAdapterItem<Enviroment>, listener: BaseRecyclerListener<BaseAdapterItem<Enviroment>>?) {
        buttonConnect.setOnClickListener {
            data.item = Enviroment("Custom", urlEditext.text.toString(), "Custom server")
            listener?.onClickListener(data, it)
        }
    }
}