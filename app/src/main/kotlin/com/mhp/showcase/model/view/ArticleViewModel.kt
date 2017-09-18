package com.mhp.showcase.model.view

import android.content.Context
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.network.GetArticleNetworkService
import com.mhp.showcase.util.BlockViewHelper
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * view model to control behavior and displayed information on the [com.mhp.showcase.fragment.StreamFragment]
 */
class ArticleViewModel {

    internal val blocks: BehaviorSubject<List<BaseBlock>> = BehaviorSubject.create()
    internal val title: BehaviorSubject<String> = BehaviorSubject.create()

    internal val active: BehaviorSubject<Boolean> = BehaviorSubject.create()
    /** needed to transform [com.mhp.showcase.block.BaseBlock] information
     * into actual [com.mhp.showcase.block.BaseBlockView]s */
    @Inject
    internal lateinit var blockViewHelper: BlockViewHelper
    /** the network to fetch the block information from the web*/
    @Inject
    internal lateinit var getArticleNetworkService: GetArticleNetworkService
    @Inject
    internal lateinit var context: Context
    private val disposables: ArrayList<Disposable> = ArrayList()

    init {
        ShowcaseApplication.graph.inject(this)
        active.subscribe({
            if (it) {
                disposables.add(
                        getArticleNetworkService.blocks.subscribeBy(onNext = {
                            val blocks: ArrayList<BaseBlock> = ArrayList()
                            it.header.title?.let(title::onNext)
                            
                            for (its in it.blocks) {
                                // skip null values
                                its?.let {
                                    // convert the blocks into views and add each view if not null
                                    if (its != null)
                                        blocks.add(its)
                                }
                            }
                            this.blocks.onNext(blocks)
                        }))
            } else {
                disposables.forEach(Disposable::dispose)
                disposables.clear()
            }
        })
    }
}