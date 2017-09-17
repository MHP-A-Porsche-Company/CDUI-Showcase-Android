package com.mhp.showcase.block.imagestream

import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.block.user.UserBlock

data class ImageStreamBlock(val id: String,
                            val user: UserBlock,
                            val imageUrl: String?,
                            val created: Long) : BaseBlock