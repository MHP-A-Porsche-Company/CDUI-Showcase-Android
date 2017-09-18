package com.mhp.showcase.navigation

import android.app.Fragment
import java.net.URI

interface Router {

    var routeFactories: HashMap<Route, RouteFactory<*>>
    var routeTarget: RouteTarget?

    enum class RouterTarget {
        navigation,
        modal
    }

    fun navigate(url: URI)
    fun navigate(route: Route, params: RouteFactory.RouteParams?, target: RouterTarget)
    fun back(target: RouterTarget)

    interface RouteTarget {
        fun showFragment(fragment: Fragment, animated: Boolean)
    }
}