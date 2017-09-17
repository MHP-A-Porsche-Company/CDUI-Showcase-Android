package com.mhp.showcase.block.text

import android.annotation.SuppressLint
import android.content.Context
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
@SuppressLint("ViewConstructor") // We only use one way to instantiate this class
@EViewGroup(R.layout.view_block_text)
open class TextBlockView(override val block: TextBlock, context: Context) : LinearLayout(context), BaseBlockView<TextBlock> {

    @ViewById(R.id.text)
    protected lateinit var textView: TextView

    /**
     * Gets called automatically after the views were injected into this class using the
     * [ViewById] annotation.
     */
    @AfterViews
    fun afterViews() {
        textView.text = block.text
    }
}
