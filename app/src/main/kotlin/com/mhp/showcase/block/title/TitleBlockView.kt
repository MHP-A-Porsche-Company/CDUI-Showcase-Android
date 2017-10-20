package com.mhp.showcase.block.title

import android.annotation.SuppressLint
import android.content.Context
import android.widget.RelativeLayout
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.block.BaseBlockView
import com.mhp.showcase.block.text.TextBlock
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

/**
 * [android.view.View] that can be used to display content of a [TextBlock]
 */
@SuppressLint("ViewConstructor") // We only use one way to instantiate this class
@EViewGroup(R.layout.view_block_title)
open class TitleBlockView(context: Context) : RelativeLayout(context), BaseBlockView<TitleBlock> {
    override val interfaceContext: Context
        get() = context

    override var block: TitleBlock? = null
        set(value) {
            field = value
            afterViews()
        }

    @ViewById(R.id.text)
    protected lateinit var textView: TextView

    /**
     * Gets called automatically after the views were injected into this class using the
     * [ViewById] annotation.
     */
    @AfterViews
    override fun afterViews() {
        textView.text = block?.text
    }
}