package com.mhp.showcase.block.articlestream

import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.model.network.User


data class ArticleStreamBlock(
        val id: String,
        val user: User,
        val imageUrl: String,
        val title: String,
        val subtitle: String,
        val created: Long
) : BaseBlock
