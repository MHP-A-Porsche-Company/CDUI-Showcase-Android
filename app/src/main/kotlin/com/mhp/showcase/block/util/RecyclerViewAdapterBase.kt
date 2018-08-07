package com.mhp.showcase.block.util

import android.support.v7.widget.RecyclerView
import java.util.*

abstract class RecyclerViewAdapterBase<T> : RecyclerView.Adapter<ViewWrapper<*>>() {

    /**
     * Set the items to be rendered
     *
     */
    private var items: List<T> = ArrayList()
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
