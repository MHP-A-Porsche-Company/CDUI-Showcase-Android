package com.mhp.showcase.block.carousel


import android.content.Context
import android.support.v4.view.ViewPager
import android.widget.LinearLayout
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.block.BaseBlockView
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById


@EViewGroup(R.layout.view_block_carousel)
open class CarouselBlockView(context: Context) : LinearLayout(context), BaseBlockView<CarouselBlock> {

    override var block: CarouselBlock? = null
        set(carouselBlock) {
            field = carouselBlock
            afterViews()
        }

    @ViewById(R.id.carousel)
    protected lateinit var pager: ViewPager
    @ViewById(R.id.text)
    protected lateinit var title: TextView
    private var adapter: CarouselBlockViewAdapter? = null

    /**
     * Gets called automatically after the views were injected into this class using the
     * [ViewById] annotation.
     */
    @AfterViews
    override fun afterViews() {
        if (this.block == null) {
            return
        }
        title.text = block!!.title
        adapter = CarouselBlockViewAdapter(context, this.block?.items!!)
        pager.clipToPadding = false
        val defaultMargin = resources.getDimensionPixelOffset(R.dimen.margin_default)
        pager.pageMargin = defaultMargin
        pager.adapter = adapter
    }
}