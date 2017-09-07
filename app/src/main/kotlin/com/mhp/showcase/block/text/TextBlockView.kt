package com.mhp.showcase.block.text

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView

import com.mhp.showcase.R
import com.mhp.showcase.block.BaseBlockView

import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById


/**
 * [android.view.View] that can be used to display content of a [TextBlock]
 */
@EViewGroup(R.layout.view_block_text)
open class TextBlockView : LinearLayout, BaseBlockView<TextBlock> {
    private var block: TextBlock? = null

    @ViewById(R.id.text)
    protected lateinit var textView: TextView

    constructor(context: Context, block: TextBlock) : super(context) {
        bind(block)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    /**
     * {@inheritDoc}
     */
    final override fun bind(block: TextBlock) {
        this.block = block
    }

    /**
     * Gets called automatically after the views were injected into this class using the
     * [ViewById] annotation.
     */
    @AfterViews
    fun afterViews() {
        textView.text = block!!.text
    }
}
