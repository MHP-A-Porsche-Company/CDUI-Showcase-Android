package com.mhp.showcase.block.image

import com.mhp.showcase.block.BaseBlock
import java.net.URL

data class ImageBlock(val id: String,
                      val text: String,
                      val imageUrl: URL?) : BaseBlock