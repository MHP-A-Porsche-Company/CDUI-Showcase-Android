package com.mhp.showcase.module

import com.mhp.showcase.activity.MainActivity
import com.mhp.showcase.block.BlockRecyclerViewAdapter
import com.mhp.showcase.block.articlestream.ArticleStreamBlockView
import com.mhp.showcase.fragment.ArticleFragment
import com.mhp.showcase.fragment.StreamFragment
import com.mhp.showcase.model.view.ArticleViewModel
import com.mhp.showcase.model.view.HomeViewModel
import com.mhp.showcase.navigation.DefaultRouter
import com.mhp.showcase.network.GetArticleNetworkService
import com.mhp.showcase.network.GetStreamNetworkService
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
    fun inject(getStreamNetworkService: GetStreamNetworkService)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(backendImageView: BackendImageView)
    fun inject(articleFragment: ArticleFragment)
    fun inject(blockRecyclerViewAdapter: BlockRecyclerViewAdapter)
    fun inject(mainActivity: MainActivity)
    fun inject(articleStreamBlockView: ArticleStreamBlockView)
    fun inject(defaultRouter: DefaultRouter)
    fun inject(articleViewModel: ArticleViewModel)
    fun inject(getArticleNetworkService: GetArticleNetworkService)
}