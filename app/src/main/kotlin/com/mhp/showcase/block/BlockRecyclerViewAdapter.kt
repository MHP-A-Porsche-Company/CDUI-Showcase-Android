package com.mhp.showcase.block

import android.content.Context
import android.view.ViewGroup
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.articlestream.ArticleStreamBlock
import com.mhp.showcase.block.articlestream.ArticleStreamBlockView
import com.mhp.showcase.block.articlestream.ArticleStreamBlockView_
import com.mhp.showcase.block.carousel.CarouselBlock
import com.mhp.showcase.block.carousel.CarouselBlockView
import com.mhp.showcase.block.carousel.CarouselBlockView_
import com.mhp.showcase.block.eventstream.EventStreamBlock
import com.mhp.showcase.block.eventstream.EventStreamBlockView
import com.mhp.showcase.block.eventstream.EventStreamBlockView_
import com.mhp.showcase.block.header.HeaderBlock
import com.mhp.showcase.block.header.HeaderBlockView
import com.mhp.showcase.block.header.HeaderBlockView_
import com.mhp.showcase.block.image.ImageBlock
import com.mhp.showcase.block.image.ImageBlockView
import com.mhp.showcase.block.image.ImageBlockView_
import com.mhp.showcase.block.imagestream.ImageStreamBlock
import com.mhp.showcase.block.imagestream.ImageStreamBlockView
import com.mhp.showcase.block.imagestream.ImageStreamBlockView_
import com.mhp.showcase.block.text.TextBlock
import com.mhp.showcase.block.text.TextBlockView
import com.mhp.showcase.block.text.TextBlockView_
import com.mhp.showcase.block.texthighlight.TextHighlightBlock
import com.mhp.showcase.block.texthighlight.TextHighlightBlockView
import com.mhp.showcase.block.texthighlight.TextHighlightBlockView_
import com.mhp.showcase.block.title.TitleBlock
import com.mhp.showcase.block.title.TitleBlockView
import com.mhp.showcase.block.title.TitleBlockView_
import com.mhp.showcase.block.user.UserBlock
import com.mhp.showcase.block.user.UserBlockView
import com.mhp.showcase.block.user.UserBlockView_
import com.mhp.showcase.block.util.RecyclerViewAdapterBase
import com.mhp.showcase.block.util.ViewWrapper
import java.util.*
import javax.inject.Inject

class BlockRecyclerViewAdapter : RecyclerViewAdapterBase<BaseBlock>() {

    @Inject
    protected lateinit var context: Context

    init {
        ShowcaseApplication.graph.inject(this)
    }

    private var blocks: List<BaseBlock> = ArrayList()

    internal inner class ArticleStreamViewHolder(itemView: ArticleStreamBlockView) : ViewWrapper<ArticleStreamBlockView>(itemView)
    internal inner class CarouselViewHolder(itemView: CarouselBlockView) : ViewWrapper<CarouselBlockView>(itemView)
    internal inner class EventStreamViewHolder(itemView: EventStreamBlockView) : ViewWrapper<EventStreamBlockView>(itemView)
    internal inner class HeaderViewHolder(itemView: HeaderBlockView) : ViewWrapper<HeaderBlockView>(itemView)
    internal inner class ImageViewHolder(itemView: ImageBlockView) : ViewWrapper<ImageBlockView>(itemView)
    internal inner class ImageStreamViewHolder(itemView: ImageStreamBlockView) : ViewWrapper<ImageStreamBlockView>(itemView)
    internal inner class TextViewHolder(itemView: TextBlockView) : ViewWrapper<TextBlockView>(itemView)
    internal inner class TextHighlightViewHolder(itemView: TextHighlightBlockView) : ViewWrapper<TextHighlightBlockView>(itemView)
    internal inner class UserViewHolder(itemView: UserBlockView) : ViewWrapper<UserBlockView>(itemView)
    internal inner class TitleViewHolder(itemView: TitleBlockView) : ViewWrapper<TitleBlockView>(itemView)


    override fun getItemViewType(position: Int): Int {
        return when {
            blocks[position] is ArticleStreamBlock -> ViewType.ARTICLE_STREAM_BLOCK.value
            blocks[position] is CarouselBlock -> ViewType.CAROUSEL_BLOCK.value
            blocks[position] is EventStreamBlock -> ViewType.EVENT_STREAM_BLOCK.value
            blocks[position] is HeaderBlock -> ViewType.HEADER_BLOCK.value
            blocks[position] is ImageBlock -> ViewType.IMAGE_BLOCK.value
            blocks[position] is ImageStreamBlock -> ViewType.IMAGE_STREAM_BLOCK.value
            blocks[position] is TextBlock -> ViewType.TEXT_BLOCK.value
            blocks[position] is TextHighlightBlock -> ViewType.TEXT_HIGHLIGHT_BLOCK.value
            blocks[position] is TitleBlock -> ViewType.TITLE_BLOCK.value
            blocks[position] is UserBlock -> ViewType.USER_BLOCK.value
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return blocks.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<*>? {
        return when (viewType) {
            ViewType.EVENT_STREAM_BLOCK.value -> EventStreamViewHolder(EventStreamBlockView_.build(context))
            ViewType.CAROUSEL_BLOCK.value -> CarouselViewHolder(CarouselBlockView_.build(context))
            ViewType.ARTICLE_STREAM_BLOCK.value -> ArticleStreamViewHolder(ArticleStreamBlockView_.build(context))
            ViewType.HEADER_BLOCK.value -> HeaderViewHolder(HeaderBlockView_.build(context))
            ViewType.IMAGE_BLOCK.value -> ImageViewHolder(ImageBlockView_.build(context))
            ViewType.IMAGE_STREAM_BLOCK.value -> ImageStreamViewHolder(ImageStreamBlockView_.build(context))
            ViewType.TEXT_BLOCK.value -> TextViewHolder(TextBlockView_.build(context))
            ViewType.TEXT_HIGHLIGHT_BLOCK.value -> TextHighlightViewHolder(TextHighlightBlockView_.build(context))
            ViewType.TITLE_BLOCK.value -> TitleViewHolder(TitleBlockView_.build(context))
            ViewType.USER_BLOCK.value -> UserViewHolder(UserBlockView_.build(context))

            else -> EventStreamViewHolder(EventStreamBlockView_.build(context)) // TODO
        }
    }

    fun setBlocks(blocks: List<BaseBlock>) {
        this.blocks = blocks
    }

    override fun onBindViewHolder(holder: ViewWrapper<*>?, position: Int) {
        when (holder?.itemViewType) {
            ViewType.ARTICLE_STREAM_BLOCK.value -> {
                val viewHolder0 = holder as ArticleStreamViewHolder
                viewHolder0.view.block = blocks[position] as ArticleStreamBlock
            }
            ViewType.CAROUSEL_BLOCK.value -> {
                val viewHolder1 = holder as CarouselViewHolder
                viewHolder1.view.block = blocks[position] as CarouselBlock
            }
            ViewType.EVENT_STREAM_BLOCK.value -> {
                val viewHolder1 = holder as EventStreamViewHolder
                viewHolder1.view.block = blocks[position] as EventStreamBlock
            }
            ViewType.HEADER_BLOCK.value -> {
                val viewHolder0 = holder as HeaderViewHolder
                viewHolder0.view.block = blocks[position] as HeaderBlock
            }
            ViewType.IMAGE_BLOCK.value -> {
                val viewHolder0 = holder as ImageViewHolder
                viewHolder0.view.block = blocks[position] as ImageBlock
            }
            ViewType.IMAGE_STREAM_BLOCK.value -> {
                val viewHolder2 = holder as ImageStreamViewHolder
                viewHolder2.view.block = blocks[position] as ImageStreamBlock
            }
            ViewType.TEXT_BLOCK.value -> {
                val viewHolder0 = holder as TextViewHolder
                viewHolder0.view.block = blocks[position] as TextBlock
            }
            ViewType.TEXT_HIGHLIGHT_BLOCK.value -> {
                val viewHolder0 = holder as TextHighlightViewHolder
                viewHolder0.view.block = blocks[position] as TextHighlightBlock
            }
            ViewType.TITLE_BLOCK.value -> {
                val viewHolder0 = holder as TitleViewHolder
                viewHolder0.view.block = blocks[position] as TitleBlock
            }
            ViewType.USER_BLOCK.value -> {
                val viewHolder0 = holder as UserViewHolder
                viewHolder0.view.block = blocks[position] as UserBlock
            }
        }
    }

    enum class ViewType(val value: Int) {
        ARTICLE_STREAM_BLOCK(2),
        EVENT_STREAM_BLOCK(0),
        CAROUSEL_BLOCK(3),
        HEADER_BLOCK(4),
        IMAGE_BLOCK(5),
        IMAGE_STREAM_BLOCK(1),
        TEXT_BLOCK(6),
        TEXT_HIGHLIGHT_BLOCK(7),
        TITLE_BLOCK(8),
        USER_BLOCK(9),
    }
}