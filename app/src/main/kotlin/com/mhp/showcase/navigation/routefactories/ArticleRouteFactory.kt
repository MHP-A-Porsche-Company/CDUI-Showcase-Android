package com.mhp.showcase.navigation.routefactories

import com.mhp.showcase.fragment.ArticleFragment
import com.mhp.showcase.fragment.ArticleFragment_
import com.mhp.showcase.navigation.RouteFactory
import java.net.URI

/**
 * Implementation of [RouteFactory] that can be used to navigate to [ArticleFragment]
 */
class ArticleRouteFactory : RouteFactory<ArticleFragment> {

    class ArticleRouteParams(val value: String) : RouteFactory.RouteParams

    override fun params(from: URI): RouteFactory.RouteParams? {
        return ArticleRouteParams(from.query.replace("id=", ""))
    }

    override fun build(params: RouteFactory.RouteParams?): ArticleFragment {
        return ArticleFragment_.builder().id((params as ArticleRouteParams).value).build()
    }
}