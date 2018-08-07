package com.mhp.showcase.activity

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.mhp.showcase.R
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.navigation.Route
import com.mhp.showcase.navigation.Router
import com.mhp.showcase.navigation.spacefactories.ArticleSpaceFactory
import com.mhp.showcase.navigation.spacefactories.StreamSpaceFactory
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

@SuppressLint("Registered")// The generated MainActivity_ is registered in the manifest file
@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity(), Router.RouteTarget {

    @ViewById(R.id.fragment_container)
    internal lateinit var fragmentContainer: FrameLayout
    @Inject
    internal lateinit var router: Router

    @AfterViews
    protected fun afterViews() {
        ShowcaseApplication.graph.inject(this)
        router.routeTarget = this // register the current class as route target
        router.spaceFactories.put(Route.STREAM, StreamSpaceFactory()) // register the stream as route
        router.spaceFactories.put(Route.ARTICLE, ArticleSpaceFactory()) // register the article detail page as route
        router.navigate(Route.STREAM, null, Router.RouterTarget.NAVIGATION) // navigate to stream
    }

    @SuppressLint("ResourceType")
    override fun showFragment(fragment: Fragment, animated: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (animated) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,R.anim.slide_in_left, R.anim.slide_out_right)
        }
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        // Don't add the very first fragment to the back stack
        if (fragmentContainer.getChildAt(0) != null) {
            // add all other views to the back stack
            fragmentTransaction.addToBackStack("foo")
        }
        fragmentTransaction.commit()
    }
}