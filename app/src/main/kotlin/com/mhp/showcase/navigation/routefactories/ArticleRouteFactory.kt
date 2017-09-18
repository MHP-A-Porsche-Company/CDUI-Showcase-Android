package com.mhp.showcase.navigation.routefactories

import com.mhp.showcase.fragment.ArticleFragment
import com.mhp.showcase.fragment.ArticleFragment_
import com.mhp.showcase.navigation.RouteFactory
import java.net.URI

class ArticleRouteFactory : RouteFactory<ArticleFragment> {
    override fun params(from: URI): RouteFactory.RouteParams? {
        return null
    }

    override fun build(params: RouteFactory.RouteParams?): ArticleFragment {
        return ArticleFragment_.builder().build()
    }
}