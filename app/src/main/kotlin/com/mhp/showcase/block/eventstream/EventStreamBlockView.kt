package com.mhp.showcase.block.eventstream

import android.annotation.SuppressLint
import android.content.Context
import android.widget.RelativeLayout
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.block.BaseBlockView
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ViewConstructor")
@EViewGroup(R.layout.view_block_event_stream)
open class EventStreamBlockView(context: Context) : RelativeLayout(context), BaseBlockView<EventStreamBlock> {

    @ViewById(R.id.title)
    protected lateinit var title: TextView
    @ViewById(R.id.subtitle)
    protected lateinit var subtitle: TextView
    @ViewById(R.id.date)
    protected lateinit var date: TextView

    override var block: EventStreamBlock? = null
        set(value) {
            field = value
            afterViews()
        }

    @AfterViews
    override fun afterViews() {
        if (block != null) {
            title.text = block?.title
            subtitle.text = block?.subtitle
            val df = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
            date.text = df.format(Date(block?.date!!)).toUpperCase()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EventStreamBlockView

        if (block != other.block) return false

        return true
    }

    override fun hashCode(): Int {
        return block?.hashCode() ?: 0
    }


}