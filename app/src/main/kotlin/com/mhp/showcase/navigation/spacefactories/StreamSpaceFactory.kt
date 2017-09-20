package com.mhp.showcase.navigation.spacefactories

import com.mhp.showcase.fragment.StreamFragment
import com.mhp.showcase.fragment.StreamFragment_
import com.mhp.showcase.navigation.SpaceFactory
import java.net.URI
/**
 * Implementation of [SpaceFactory] that can be used to navigate to [StreamFragment]
 */
class StreamSpaceFactory : SpaceFactory<StreamFragment> {
    override fun params(from: URI): SpaceFactory.SpaceParams? {
        return null
    }

    override fun build(params: SpaceFactory.SpaceParams?): StreamFragment {
        return StreamFragment_.builder().build()
    }
}