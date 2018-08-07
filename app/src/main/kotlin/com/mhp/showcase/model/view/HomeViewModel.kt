package com.mhp.showcase.model.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.network.GetStreamNetworkService
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * view model to control behavior and displayed information on the [com.mhp.showcase.fragment.StreamFragment]
 */
class HomeViewModel : ViewModel() {

    internal val blocks = MutableLiveData<List<BaseBlock>>()
    internal val title = MutableLiveData<String>()

    /** needed to transform [com.mhp.showcase.block.BaseBlock] information
     * into actual [com.mhp.showcase.block.BaseBlockView]s */
    /** the network to fetch the block information from the web*/
    @Inject
    internal lateinit var getStreamNetworkService: GetStreamNetworkService
    private val disposables: ArrayList<Disposable> = ArrayList()

    init {
        ShowcaseApplication.graph.inject(this)
        disposables.add(
                getStreamNetworkService.blocks.subscribeBy(onNext = {
                    val blocks: ArrayList<BaseBlock> = ArrayList()
                    it.header.title?.let(title::postValue)
                    for (its in it.blocks) {
                        // skip null values
                        its.let {
                            // convert the blocks into views and add each view if not null
                            blocks.add(its)
                        }
                    }
                    this.blocks.postValue(blocks)
                }))
    }

    override fun onCleared() {
        disposables.forEach(Disposable::dispose)
        disposables.clear()
        super.onCleared()
    }
}