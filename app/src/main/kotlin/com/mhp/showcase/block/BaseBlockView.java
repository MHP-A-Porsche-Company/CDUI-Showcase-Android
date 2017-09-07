package com.mhp.showcase.block;


public interface BaseBlockView<T extends BaseBlock> {

    /**
     * Bind the instance of {@link T} to the view. -> Take the values of the item and display them in the view
     *
     * @param block The content to be displayed
     */
    void bind(T block);
}
