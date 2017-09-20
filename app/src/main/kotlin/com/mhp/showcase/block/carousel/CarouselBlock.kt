package com.mhp.showcase.block.carousel

import com.mhp.showcase.block.BaseBlock

data class CarouselBlock(val id: String,
                         val title: String,
                         val items: ArrayList<CarouselItem>) : BaseBlock