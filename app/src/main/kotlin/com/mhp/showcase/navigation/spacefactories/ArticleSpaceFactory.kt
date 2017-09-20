package com.mhp.showcase.navigation.spacefactories

import com.mhp.showcase.fragment.ArticleFragment
import com.mhp.showcase.fragment.ArticleFragment_
import com.mhp.showcase.navigation.SpaceFactory
import java.net.URI

/**
 * Implementation of [SpaceFactory] that can be used to navigate to [ArticleFragment]
 */
class ArticleSpaceFactory : SpaceFactory<ArticleFragment> {

    class ArticleSpaceParams(val value: String) : SpaceFactory.SpaceParams

    override fun params(from: URI): SpaceFactory.SpaceParams? {
        return ArticleSpaceParams(from.query.replace("id=", ""))
    }

    override fun build(params: SpaceFactory.SpaceParams?): ArticleFragment {
        return ArticleFragment_.builder().id((params as ArticleSpaceParams).value).build()
    }
}