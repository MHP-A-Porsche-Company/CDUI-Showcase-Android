package com.mhp.showcase.navigation

import android.support.v4.app.Fragment
import java.net.URI

/**
 * Defines a factory
 */
interface SpaceFactory<out T : Fragment> {

    interface SpaceParams

    fun params(from: URI): SpaceParams?
    fun build(params: SpaceParams?): T
}