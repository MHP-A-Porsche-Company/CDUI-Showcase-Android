package com.mhp.showcase.block.header

import android.annotation.SuppressLint
import android.content.Context
import android.widget.RelativeLayout
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.block.BaseBlockView
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@SuppressLint("ViewConstructor")
@EViewGroup(R.layout.view_block_header)
open class HeaderBlockView(context: Context) : RelativeLayout(context), BaseBlockView<HeaderBlock> {
    override val interfaceContext: Context
        get() = context

    @ViewById(R.id.title)
    protected lateinit var title: TextView
    @ViewById(R.id.subtitle)
    protected lateinit var subtitle: TextView

    override var block: HeaderBlock? = null
        set(value) {
            field = value
            afterViews()
        }

    @AfterViews
    override fun afterViews() {
        if (block != null) {
            title.text = block?.title
            subtitle.text = block?.subtitle
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HeaderBlockView

        if (block != other.block) return false

        return true
    }

    override fun hashCode(): Int {
        return block?.hashCode() ?: 0
    }


}