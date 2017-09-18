package com.mhp.showcase.navigation.routefactories

import com.mhp.showcase.fragment.StreamFragment
import com.mhp.showcase.fragment.StreamFragment_
import com.mhp.showcase.navigation.RouteFactory
import java.net.URI

class StreamRouteFactory : RouteFactory<StreamFragment> {
    override fun params(from: URI): RouteFactory.RouteParams? {
        return null
    }

    override fun build(params: RouteFactory.RouteParams?): StreamFragment {
        return StreamFragment_.builder().build()
    }
}