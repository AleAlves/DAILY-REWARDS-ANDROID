package br.com.aleson.daily.rewards.app.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    lateinit var listItems: MutableList<T>
    var listener: BaseRecyclerListener<T>? = null

    constructor(listener: BaseRecyclerListener<T>) {
        listItems = mutableListOf()
        this.listener = listener
    }

    constructor(listItems: MutableList<T>, listener: BaseRecyclerListener<T>) {
        this.listItems = listItems
        this.listener = listener
    }

    constructor(listItems: MutableList<T>) {
        this.listItems = listItems
    }

    constructor() {
        listItems = mutableListOf()
    }

    fun setItems(listItems: MutableList<T>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return getViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
            , viewType
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenericBinder<T>).bind(listItems[position], listener)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, listItems[position])
    }

    /**
     * Abstract function to set layout id in viewholder
     *
     * @param position : item position
     * @param obj: Generic any type of data.
     */
    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    fun clear() {
        listItems = mutableListOf()
    }

    fun add(newItems: List<T>) {
        addAndNotify(newItems)
    }

    fun add(newItem: T) {
        addAndNotify(mutableListOf(newItem))
    }

    private fun addAndNotify(newItems: List<T>) {
        if (listItems.isEmpty()) {
            listItems.addAll(newItems)
        } else {
            listItems.addAll(listItems.size, newItems)
        }
        this.notifyDataSetChanged()
    }
}

interface BaseRecyclerListener<T> {

    /**
     * Function to catch onclicklistener in viewholder
     *
     * @param data: Generic any type of data
     * @param v : View clicked
     * @param code : Code asigned to view
     */
    fun onClickListener(data: T, v: View)
}

interface GenericBinder<T> {
    /**
     * Function to bind data in viewholder
     *
     * @param data : Generic Any type of data declared in interface
     * @param listener : Onclick listener with Any tye of data declared in interface
     */
    fun bind(data: T,listener: BaseRecyclerListener<T>?)
}