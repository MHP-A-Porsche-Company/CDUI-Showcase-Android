package com.mhp.showcase.block.carousel

import com.mhp.showcase.block.BaseBlock
import java.net.URI

data class CarouselItem(val id: String,
                        val text: String,
                        val title: String,
                        val imageUrl: URI?
) : BaseBlock