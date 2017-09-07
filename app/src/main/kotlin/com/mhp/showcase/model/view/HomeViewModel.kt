package com.mhp.showcase.model.view

import android.content.Context
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.BaseBlockView
import com.mhp.showcase.network.GetBlocksNetworkService
import com.mhp.showcase.util.BlockViewHelper
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * view model to control behavior and displayed information on the [com.mhp.showcase.fragment.HomeFragment]
 */
class HomeViewModel {

    val blocks: BehaviorSubject<ArrayList<BaseBlockView<*>>> = BehaviorSubject.create()
    val active: BehaviorSubject<Boolean> = BehaviorSubject.create()

    @Inject
    internal lateinit var blockViewHelper: BlockViewHelper // needed to transform block information into actual block views
    @Inject
    internal lateinit var getBlocksNetworkService: GetBlocksNetworkService // the network to fetch the block information from the web
    @Inject
    internal lateinit var context: Context

    init {
        ShowcaseApplication.graph.inject(this)
        active.subscribe({
            if (it) getBlocksNetworkService.blocks.subscribeBy(onNext = {
                val blockViews: ArrayList<BaseBlockView<*>> = ArrayList()
                for (singleBlock in it.blocks) {
                    blockViewHelper.getBlockView(singleBlock, context)?.let(blockViews::add)
                }
                blocks.onNext(blockViews)
            })
        })
    }
}