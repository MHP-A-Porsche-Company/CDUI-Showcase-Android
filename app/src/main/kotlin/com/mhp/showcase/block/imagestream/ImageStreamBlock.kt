package com.mhp.showcase.block.imagestream

import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.model.network.User

data class ImageStreamBlock(val id: String,
                            val user: User,
                            val imageUrl: String?,
                            val created: Long) : BaseBlock