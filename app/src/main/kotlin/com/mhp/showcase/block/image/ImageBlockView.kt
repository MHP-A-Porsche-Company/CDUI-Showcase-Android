package com.mhp.showcase.block.image

import android.annotation.SuppressLint
import android.content.Context
import android.widget.RelativeLayout
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.block.BaseBlockView
import com.mhp.showcase.view.BackendImageView
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@SuppressLint("ViewConstructor")
@EViewGroup(R.layout.view_block_image)
open class ImageBlockView(context: Context) : RelativeLayout(context), BaseBlockView<ImageBlock> {

    @ViewById(R.id.caption)
    protected lateinit var text: TextView

    @ViewById(R.id.image)
    protected lateinit var articleImageView: BackendImageView


    override var block: ImageBlock? = null
        set(value) {
            field = value
            afterViews()
        }

    @AfterViews
    override fun afterViews() {
        if (block == null) {
            return
        }
        if (block?.imageUrl != null) {
            articleImageView.url = block?.imageUrl
        }
        text.text = block?.text
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageBlockView

        if (block != other.block) return false

        return true
    }

    override fun hashCode(): Int {
        return block?.hashCode() ?: 0
    }


}