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
open class ArticleStreamBlockView(override val block: ArticleStreamBlock, context: Context) : RelativeLayout(context), BaseBlockView<ArticleStreamBlock> {

    @ViewById(R.id.user)
    protected lateinit var userView: UserView
    @ViewById(R.id.article_image)
    protected lateinit var articleImageView: BackendImageView
    @ViewById(R.id.headline)
    protected lateinit var headline: TextView
    @ViewById(R.id.subheadline)
    protected lateinit var subHeadline: TextView

    @AfterViews
    protected fun afterViews() {
        userView.user = block.user
        if (block.imageUrl == null) {
            articleImageView.setVisibility(View.GONE)
        } else {
            articleImageView.url = block.imageUrl
        }
        headline.text = block.title
        headline.text = block.title
        subHeadline.text = block.subtitle
    }
}