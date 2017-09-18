package com.mhp.showcase.navigation

import android.app.Fragment
import com.mhp.showcase.util.Constants
import java.net.URI

/**
 * Interface to define a central router that can be used to navigate between views or spaces in the
 * app or to navigate to an external target.
 */
interface Router {

    /**
     * The set of instances [RouteFactory] with matching instances of [Route] to navigate to internal targets
     */
    var routeFactories: HashMap<Route, RouteFactory<*>>
    var routeTarget: RouteTarget?

    /**
     * The [Router.RouterTarget] controls in which container or context the container
     * the view gets inflated in
     */
    enum class RouterTarget {
        navigation,
        modal
    }

    /**
     * Navigate to a navigation target using a given [URI]. The URI can define either an internal
     * target or a website.
     * For an internal target, the URI scheme must match [Constants.APP_SCHEME]. The URI path
     * defines the internal target.
     */
    fun navigate(url: URI)

    /**
     * Navigate to an internal target using a [Route]. (e.g. an ID). The [Router.RouterTarget]
     *
     * @param route The [Route] defines the target
     * @param params The optional [RouteFactory.RouteParams] can be used to deliver a payload.
     * @param target The [Router.RouterTarget] controls in which container or context the container
     * the view gets inflated in
     */
    fun navigate(route: Route, params: RouteFactory.RouteParams?, target: RouterTarget)

    fun back(target: RouterTarget)

    interface RouteTarget {
        fun showFragment(fragment: Fragment, animated: Boolean)
    }
}