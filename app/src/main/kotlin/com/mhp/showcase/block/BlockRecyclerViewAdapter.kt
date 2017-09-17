package com.mhp.showcase.block

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.articlestream.ArticleStreamBlock
import com.mhp.showcase.block.articlestream.ArticleStreamBlockView
import com.mhp.showcase.block.articlestream.ArticleStreamBlockView_
import com.mhp.showcase.block.eventstream.EventStreamBlock
import com.mhp.showcase.block.eventstream.EventStreamBlockView
import com.mhp.showcase.block.eventstream.EventStreamBlockView_
import com.mhp.showcase.block.imagestream.ImageStreamBlock
import com.mhp.showcase.block.imagestream.ImageStreamBlockView
import com.mhp.showcase.block.imagestream.ImageStreamBlockView_
import com.mhp.showcase.block.util.RecyclerViewAdapterBase
import com.mhp.showcase.block.util.ViewWrapper
import java.util.*
import javax.inject.Inject

class BlockRecyclerViewAdapter : RecyclerViewAdapterBase<BaseBlock, View>() {

    @Inject
    protected lateinit var context: Context

    init {
        ShowcaseApplication.graph.inject(this)
    }

    private var blocks: List<BaseBlock> = ArrayList()

    internal inner class ArticleStreamViewHolder(itemView: ArticleStreamBlockView) : ViewWrapper<ArticleStreamBlockView>(itemView)

    internal inner class EventStreamViewHolder(itemView: EventStreamBlockView) : ViewWrapper<EventStreamBlockView>(itemView)

    internal inner class ImageStreamViewHolder(itemView: ImageStreamBlockView) : ViewWrapper<ImageStreamBlockView>(itemView)

    override fun getItemViewType(position: Int): Int {
        return when {
            blocks[position] is EventStreamBlock -> 0
            blocks[position] is ImageStreamBlock -> 1
            blocks[position] is ArticleStreamBlock -> 2
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return blocks.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<*>? {
        return when (viewType) {
            0 -> EventStreamViewHolder(EventStreamBlockView_.build(context))
            1 -> ImageStreamViewHolder(ImageStreamBlockView_.build(context))
            2 -> ArticleStreamViewHolder(ArticleStreamBlockView_.build(context))
            else -> EventStreamViewHolder(EventStreamBlockView_.build(context)) // TODO
        }
    }

    fun setBlocks(blocks: List<BaseBlock>) {
        this.blocks = blocks
    }
    
    override fun onBindViewHolder(holder: ViewWrapper<View>?, position: Int) {
        when (holder?.itemViewType) {
            0 -> {
                val viewHolder1 = holder as EventStreamViewHolder
                viewHolder1.view.block = blocks[position] as EventStreamBlock
            }
            1 -> {
                val viewHolder2 = holder as ImageStreamViewHolder
                viewHolder2.view.block = blocks[position] as ImageStreamBlock
            }
            2 -> {
                val viewHolder0 = holder as ArticleStreamViewHolder
                viewHolder0.view.block = blocks[position] as ArticleStreamBlock
            }
        }
    }
}