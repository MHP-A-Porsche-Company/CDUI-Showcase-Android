package com.mhp.showcase.navigation

import android.app.Fragment
import java.net.URI

/**
 * Created by tobiasweidinger on 18.09.17.
 */
interface RouteFactory<out T : Fragment> {

    interface RouteParams

    fun params(from: URI): RouteParams?
    fun build(params: RouteParams?): T


}