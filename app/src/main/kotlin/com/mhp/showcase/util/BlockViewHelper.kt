package com.mhp.showcase.util

import android.content.Context
import android.util.Log
import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.block.BaseBlockView
import com.mhp.showcase.block.text.TextBlock
import com.mhp.showcase.block.text.TextBlockView_


/**
 * Helper class to get an instance of a matching implementation of [com.mhp.showcase.block.BaseBlockView] for a
 * given instance of an implementation of [com.mhp.showcase.block.BaseBlock]
 */
class BlockViewHelper {

    private val TAG = BlockViewHelper::class.java.simpleName

    /**
     * Get an instance of a matching implementation of [BaseBlockView] for a given instance
     * of an implementation of [BaseBlock]
     *
     * @param block   The block to be displayed in an implementation of [BaseBlockView]
     * @param context The context to be used during instantiation
     * @return A matching instance of [BaseBlockView]
     */
    fun getBlockView(block: BaseBlock, context: Context): BaseBlockView<*>? {
        var blockView: BaseBlockView<*>? = null
        when (block) {
            is TextBlock -> blockView = TextBlockView_.build(block, context)
            else -> Log.w(TAG, "Could not find view for block " + block.toString())
        }
        return blockView
    }
}
