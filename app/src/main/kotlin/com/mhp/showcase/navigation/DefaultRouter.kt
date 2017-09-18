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


class DefaultRouter : Router {


    @Inject
    protected lateinit var context: Context

    override var routeFactories: HashMap<Route, RouteFactory<*>> = HashMap()
        get
        set(value) {}

    override var routeTarget: RouteTarget? = null

    override fun back(target: Router.RouterTarget) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun navigate(url: URI) {
        if (url.scheme != Constants.APP_SCHEME) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url.toString())
            i.addFlags(FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
            return
        }
        for ((key, value) in routeFactories) {
            if (key.value == url.path.toString()) {
                val fragment = value.build(value.params(url))
                routeTarget?.showFragment(fragment, true)
                return
            }
        }
    }

    override fun navigate(route: Route, params: RouteFactory.RouteParams?, target: Router.RouterTarget) {
        val routeFactory = routeFactories[route] ?: return
        val fragment = routeFactory.build(params)
        routeTarget?.showFragment(fragment, true)
    }

    init {
        ShowcaseApplication.graph.inject(this)
    }
}

