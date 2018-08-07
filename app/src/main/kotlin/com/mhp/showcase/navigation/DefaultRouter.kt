package com.mhp.showcase.navigation

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.navigation.Router.RouteTarget
import com.mhp.showcase.util.Constants
import java.net.URI
import javax.inject.Inject

/**
 *  Actual implementation of [Router] that can be used to navigate to both external and internal targets
 */
class DefaultRouter : Router {
    @Inject
    internal lateinit var context: Context


    override var spaceFactories: HashMap<Route, SpaceFactory<*>> = HashMap()

    override var routeTarget: RouteTarget? = null


    override fun navigate(url: URI) {
        if (url.scheme != Constants.APP_SCHEME) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url.toString())
            i.addFlags(FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
            return
        }
        for ((key, value) in spaceFactories) {
            if (key.value == url.path.toString()) {
                val fragment = value.build(value.params(url))
                routeTarget?.showFragment(fragment, true)
                return
            }
        }
    }

    override fun navigate(route: Route, params: SpaceFactory.SpaceParams?, target: Router.RouterTarget) {
        val routeFactory = spaceFactories[route] ?: return
        val fragment = routeFactory.build(params)
        routeTarget?.showFragment(fragment, true)
    }

    init {
        ShowcaseApplication.graph.inject(this)
    }
}

