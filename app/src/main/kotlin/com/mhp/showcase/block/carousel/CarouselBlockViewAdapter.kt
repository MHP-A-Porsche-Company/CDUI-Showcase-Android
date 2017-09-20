package com.mhp.showcase.block.carousel

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.mhp.showcase.R
import com.mhp.showcase.view.BackendImageView


class CarouselBlockViewAdapter(context: Context,
                               /**
                                * The item to be displayed
                                */
                               private val item: List<CarouselItem>) : PagerAdapter() {

    private val mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getPageWidth(position: Int): Float {
        return (super.getPageWidth(position) / 2 - 0.06).toFloat()
    }

    /**
     * {@inheritDoc}
     */
    override fun getCount(): Int {
        return item.size
    }

    /**
     * {@inheritDoc}
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    /**
     * {@inheritDoc}
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.view_pager_carousel_block_item, container, false)
        val title = itemView.findViewById<TextView>(R.id.title)
        val titleText = item[position].title
        title.text = titleText
        val text = itemView.findViewById<TextView>(R.id.text)
        val textText = item[position].text
        text.text = textText
        val imageView = itemView.findViewById<BackendImageView>(R.id.pagerImg)
        imageView.url = item[position].imageUrl
        container.addView(itemView)
        return itemView
    }

    /**
     * {@inheritDoc}
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}