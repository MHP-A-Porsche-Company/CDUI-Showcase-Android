package com.mhp.showcase.block.carousel

import com.mhp.showcase.block.BaseBlock

data class CarouselItem(val id: String,
                        val text: String,
                        val imageUrl: String?
) : BaseBlock