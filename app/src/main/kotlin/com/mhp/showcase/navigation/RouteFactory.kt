package com.mhp.showcase.navigation

import android.app.Fragment
import java.net.URI

interface RouteFactory<out T : Fragment> {

    interface RouteParams

    fun params(from: URI): RouteParams?
    fun build(params: RouteParams?): T


}