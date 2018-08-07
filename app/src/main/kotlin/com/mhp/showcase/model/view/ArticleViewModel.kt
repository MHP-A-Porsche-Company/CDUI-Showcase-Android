package com.mhp.showcase.model.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.network.GetArticleNetworkService
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import java.net.URI
import javax.inject.Inject

/**
 * view model to control behavior and displayed information on the [com.mhp.showcase.fragment.StreamFragment]
 */
class ArticleViewModel : ViewModel() {

    internal val blocks = MutableLiveData<List<BaseBlock>>()
    internal val title = MutableLiveData<String>()
    internal val image = MutableLiveData<URI>()

    var id: String? = null
        set(value) {
            field = value
            if (value != null) {
                disposables.add(
                        getArticleNetworkService.getBlocks(value).subscribeBy(onNext = {
                            val blocks: ArrayList<BaseBlock> = ArrayList()
                            it.header.title?.let(title::postValue)
                            it.header.imageUrl?.let(image::postValue)


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
        }

    /** the network to fetch the block information from the web*/
    @Inject
    internal lateinit var getArticleNetworkService: GetArticleNetworkService
    private val disposables: ArrayList<Disposable> = ArrayList()


    init {
        ShowcaseApplication.graph.inject(this)
    }

    override fun onCleared() {
        disposables.forEach(Disposable::dispose)
        disposables.clear()
        super.onCleared()
    }
}