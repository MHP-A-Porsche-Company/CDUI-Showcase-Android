package com.mhp.showcase.module

import com.mhp.showcase.block.BlockRecyclerViewAdapter
import com.mhp.showcase.fragment.ArticleFragment
import com.mhp.showcase.fragment.StreamFragment
import com.mhp.showcase.model.view.HomeViewModel
import com.mhp.showcase.network.GetBlocksNetworkService
import com.mhp.showcase.view.BackendImageView
import dagger.Component
import javax.inject.Singleton

/**
 * Assigns references in activities, services, or fragments to have access to singletons previously defined.
 */
@Singleton
@Component(modules = arrayOf(ShowcaseModule::class))
interface ShowcaseComponent {
    fun inject(streamFragment: StreamFragment)
    fun inject(getBlocksNetworkService: GetBlocksNetworkService)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(backendImageView: BackendImageView)
    fun inject(articleFragment: ArticleFragment)
    fun inject(blockRecyclerViewAdapter: BlockRecyclerViewAdapter)
}