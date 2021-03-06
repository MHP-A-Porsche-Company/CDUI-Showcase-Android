package com.mhp.showcase.block.image

import com.mhp.showcase.block.BaseBlock
import java.net.URI

data class ImageBlock(override val id: String,
                      val text: String,
                      val imageUrl: URI?) : BaseBlock