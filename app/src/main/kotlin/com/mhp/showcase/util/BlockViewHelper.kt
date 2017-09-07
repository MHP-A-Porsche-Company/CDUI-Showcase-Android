package com.mhp.showcase.util


import android.content.Context
import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.block.BaseBlockView
import com.mhp.showcase.block.text.TextBlock
import com.mhp.showcase.block.text.TextBlockView_
import io.reactivex.annotations.NonNull


/**
 * Helper class to get an instance of a matching implementation of [com.mhp.showcase.block.BaseBlockView] for a
 * given instance of an implementation of [com.mhp.showcase.block.BaseBlock]
 */
class BlockViewHelper {


    /**
     * Get an instance of a matching implementation of [BaseBlockView] for a given instance
     * of an implementation of [BaseBlock]
     *
     * @param block   The block to be displayed in an implementation of [BaseBlockView]
     * @param context The context to be used during instantiation
     * @return A matching instance of [BaseBlockView]
     */
    fun getBlockView(@NonNull block: BaseBlock, @NonNull context: Context): BaseBlockView<*>? {
        var blockView: BaseBlockView<*>? = null
        if (block is TextBlock) {
            blockView = TextBlockView_.build(context, block)
        }
        //        } else if (block instanceof ImageCarouselBlock) {
        //            blockView = ImageCarouselBlockView_.build(context, (ImageCarouselBlock) block, fragmentManager);
        //        } else if (block instanceof QuoteBlock) {
        //            blockView = QuoteBlockView_.build(context, (QuoteBlock) block);
        //        } else if (block instanceof TextBlock) {
        //            blockView = TextBlockView_.build(context, (TextBlock) block);
        //        } else if (block instanceof HighlightBlock) {
        //            blockView = HighlightBlockView_.build(context, (HighlightBlock) block);
        //        } else if (block instanceof DescriptionBlock) {
        //            blockView = DescriptionBlockView_.build(context, (DescriptionBlock) block);
        //        } else if (block instanceof HeadlineBlock) {
        //            blockView = HeadlineBlockView_.build(context, (HeadlineBlock) block);
        //        } else if (block instanceof ButtonBlock) {
        //            blockView = ButtonBlockView_.build(context, (ButtonBlock) block);
        //        } else if (block instanceof ListBlock) {
        //            blockView = ListBlockView_.build(context, (ListBlock) block);
        //        } else if (block instanceof TextHighlightBlock) {
        //            blockView = TextHighlightView_.build(context, (TextHighlightBlock) block);
        //        } else if (block instanceof ImageGalleryBlock) {
        //            blockView = ImageGalleryBlockView_.build(context, (ImageGalleryBlock) block, fragmentManager);
        //        } else if (block instanceof AudioGuideBlock) {
        //            blockView = AudioGuideBlockView_.build(context, (AudioGuideBlock) block);
        //        } else if (block instanceof PagerBlock) {
        //            blockView = PagerBlockView_.build(context, (PagerBlock) block);
        //        } else if (block instanceof DividerBlock) {
        //            blockView = DividerBlockView_.build(context);
        //        } else if (block instanceof DividerWithMarginBlock) {
        //            blockView = DividerWithMarginBlockView_.build(context);
        //        } else if (block instanceof HeroBlock) {
        //            blockView = HeroBlockView_.build(context, (HeroBlock) block);
        //        } else if (block instanceof AlertBlock) {
        //            blockView = AlertBlockView_.build(context, (AlertBlock) block);
        //        } else if (block instanceof PoiFavoritesBlock) {
        //            blockView = PoiFavoritesBlockView_.build(context, (PoiFavoritesBlock) block);
        //        } else if (block instanceof ListItemBlock) {
        //            blockView = ListItemBlockView_.build(context, (ListItemBlock) block);
        //        } else if (block instanceof LocalImageBlock) {
        //            blockView = LocalImageBlockView_.build(context, (LocalImageBlock) block);
        //        } else if (block instanceof ImprintLinkBlock) {
        //            blockView = ImprintLinkBlockView_.build(context, (ImprintLinkBlock) block);
        //        }
        return blockView
    }
}
