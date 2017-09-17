package com.mhp.showcase.block.articlestream

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.block.BaseBlockView
import com.mhp.showcase.view.BackendImageView
import com.mhp.showcase.view.UserView
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@SuppressLint("ViewConstructor")
@EViewGroup(R.layout.view_block_article_stream)
open class ArticleStreamBlockView(context: Context) : RelativeLayout(context), BaseBlockView<ArticleStreamBlock> {

    override var block: ArticleStreamBlock? = null
        set(value) {
            field = value
            afterViews()
        }


    @ViewById(R.id.user)
    protected lateinit var userView: UserView
    @ViewById(R.id.article_image)
    protected lateinit var articleImageView: BackendImageView
    @ViewById(R.id.headline)
    protected lateinit var headline: TextView
    @ViewById(R.id.subheadline)
    protected lateinit var subHeadline: TextView

    @AfterViews
    override fun afterViews() {
        userView.user = block?.user
        if (block?.imageUrl == null) {
            articleImageView.setVisibility(View.GONE)
        } else {
            articleImageView.url = block?.imageUrl
        }
        headline.text = block?.title
        headline.text = block?.title
        subHeadline.text = block?.subtitle
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticleStreamBlockView

        if (block != other.block) return false

        return true
    }

    override fun hashCode(): Int {
        return block?.hashCode() ?: 0
    }

}