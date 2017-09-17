package com.mhp.showcase.network.model

import com.mhp.showcase.block.BaseBlock

data class ContentResponse(
        val id: String,
        val type: ContentType,
        val header: Header,
        val blocks: List<BaseBlock>
)