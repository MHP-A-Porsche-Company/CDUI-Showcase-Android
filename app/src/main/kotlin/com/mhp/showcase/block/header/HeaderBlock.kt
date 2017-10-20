package com.mhp.showcase.block.header

import com.mhp.showcase.block.BaseBlock

data class HeaderBlock(override val id: String,
                       val title: String,
                       val subtitle: String) : BaseBlock