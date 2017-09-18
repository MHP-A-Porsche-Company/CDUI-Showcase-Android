package com.mhp.showcase.block.imagestream

import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.model.network.User
import java.net.URI

data class ImageStreamBlock(val id: String,
                            val user: User,
                            val imageUrl: URI?,
                            val created: Long) : BaseBlock