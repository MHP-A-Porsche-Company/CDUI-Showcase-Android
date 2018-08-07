package com.mhp.showcase.block.title

import com.mhp.showcase.block.BaseBlock

data class TitleBlock(override val id: String,
                      val text: String) : BaseBlock