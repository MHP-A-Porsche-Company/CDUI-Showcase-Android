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


) : BaseBlock {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + subtitle.hashCode()
        result = 31 * result + created.hashCode()
        return result
    }
}
