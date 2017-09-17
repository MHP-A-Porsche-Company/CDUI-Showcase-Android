package com.mhp.showcase.block.imagestream

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import com.mhp.showcase.R
import com.mhp.showcase.block.BaseBlockView
import com.mhp.showcase.view.BackendImageView
import com.mhp.showcase.view.UserView
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@SuppressLint("ViewConstructor")
@EViewGroup(R.layout.view_block_image_stream)
open class ImageStreamBlockView(context: Context) : RelativeLayout(context), BaseBlockView<ImageStreamBlock> {

    @ViewById(R.id.user)
    protected lateinit var userView: UserView
    @ViewById(R.id.article_image)
    protected lateinit var articleImageView: BackendImageView


    override var block: ImageStreamBlock? = null
        set(value) {
            field = value
            afterViews()
        }

    @AfterViews
    override fun afterViews() {
        userView.user = block?.user
        if (block == null) {
            return
        }
        if (block?.imageUrl == null) {
            articleImageView.setVisibility(View.GONE)
        } else {
            articleImageView.url = block?.imageUrl
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageStreamBlockView

        if (block != other.block) return false

        return true
    }

    override fun hashCode(): Int {
        return block?.hashCode() ?: 0
    }


}