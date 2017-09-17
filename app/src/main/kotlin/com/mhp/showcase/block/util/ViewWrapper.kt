package com.mhp.showcase.block.util

import android.support.v7.widget.RecyclerView
import android.view.View

open class ViewWrapper<V : View> protected constructor(val view: V) : RecyclerView.ViewHolder(view)
