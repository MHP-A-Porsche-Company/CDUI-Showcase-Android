package com.mhp.showcase.block


interface BaseBlockView<in T : BaseBlock> {

    /**
     * Bind the instance of [T] to the view. -> Take the values of the item and display them in the view
     *
     * @param block The content to be displayed
     */
    fun bind(block: T)
}
