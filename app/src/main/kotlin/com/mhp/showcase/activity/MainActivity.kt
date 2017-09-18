package com.mhp.showcase.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.widget.FrameLayout
import com.mhp.showcase.R
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.navigation.Route
import com.mhp.showcase.navigation.Router
import com.mhp.showcase.navigation.routefactories.ArticleRouteFactory
import com.mhp.showcase.navigation.routefactories.StreamRouteFactory
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

@SuppressLint("Registered")// The generated MainActivity_ is registered in the manifest file
@EActivity(R.layout.activity_main)
open class MainActivity : Activity(), Router.RouteTarget {


    @ViewById(R.id.fragment_container)
    internal lateinit var fragmentContainer: FrameLayout

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @AfterViews
    protected fun afterViews(){
        ShowcaseApplication.graph.inject(this)
        router.routeTarget = this // register the current class as route target
        router.routeFactories.put(Route.stream, StreamRouteFactory()) // register the stream as route
        router.routeFactories.put(Route.articleDetail, ArticleRouteFactory()) // register the article detail page as route
        router.navigate(Route.stream, null, Router.RouterTarget.navigation) // navigate to stream
    }

    override fun showFragment(fragment: Fragment, animated: Boolean) {
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }
}