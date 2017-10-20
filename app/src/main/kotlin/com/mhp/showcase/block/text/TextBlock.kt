package com.mhp.showcase.block.text

import com.mhp.showcase.block.BaseBlock

data class TextBlock(
        override val id: String,
        val text: String
) : BaseBlock
