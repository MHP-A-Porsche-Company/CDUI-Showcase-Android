package com.mhp.showcase.block

/**
 * Actual view to be rendered in a block layout of a [android.app.Fragment] or [android.app.Activity]
 */
interface BaseBlockView<out T : BaseBlock> {
    val block: T
}
