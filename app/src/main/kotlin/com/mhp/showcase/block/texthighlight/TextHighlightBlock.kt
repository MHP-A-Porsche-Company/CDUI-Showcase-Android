package com.mhp.showcase.block.texthighlight

import com.mhp.showcase.block.BaseBlock

data class TextHighlightBlock(override val id: String,
                              val text: String) : BaseBlock