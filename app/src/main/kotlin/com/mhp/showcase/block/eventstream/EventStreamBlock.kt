package com.mhp.showcase.block.eventstream

import com.mhp.showcase.block.BaseBlock

data class EventStreamBlock(override val id: String,
                            val title: String,
                            val subtitle: String,
                            val date: Long) : BaseBlock
