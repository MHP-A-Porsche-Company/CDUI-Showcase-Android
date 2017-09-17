package com.mhp.showcase.block.util

import android.support.v7.widget.RecyclerView
import android.view.View
import java.util.*

abstract class RecyclerViewAdapterBase<T, V : View> : RecyclerView.Adapter<ViewWrapper<*>>() {


    /**
     * Set the items to be rendered
     *
     * @param items The list of items to be rendered
     */
    var items: List<T> = ArrayList()
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    /**
     * {@inheritDoc}
     */
    override fun getItemCount(): Int {
        return this.items.size
    }

}
