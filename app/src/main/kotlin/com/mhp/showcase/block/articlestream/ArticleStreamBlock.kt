package com.mhp.showcase.block.articlestream

import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.model.network.User
import java.net.URI


data class ArticleStreamBlock(
        override val id: String,
        val user: User,
        val imageUrl: URI,
        val title: String,
        val subtitle: String,
        val created: Long,
        val target: URI

) : BaseBlock