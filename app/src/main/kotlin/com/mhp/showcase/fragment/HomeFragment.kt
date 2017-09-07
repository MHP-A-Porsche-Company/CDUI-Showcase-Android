package com.mhp.showcase.fragment

import android.app.Fragment
import android.view.View
import android.widget.LinearLayout
import com.mhp.showcase.R
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.model.view.HomeViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

@EFragment(R.layout.fragment_home)
open class HomeFragment : Fragment() {

    // The area to display the blocks in
    @ViewById(R.id.block_area)
    protected lateinit var blockArea: LinearLayout
    // The view model to get the values to display from
    @Inject
    protected lateinit var homeViewModel: HomeViewModel

    // to keep track on the open subscriptions
    private val disposables: ArrayList<Disposable> = ArrayList()

    /**
     * Gets called after the view is inflated and the references are bound to the code
     */
    @AfterViews
    protected fun afterViews() {
        ShowcaseApplication.graph.inject(this)
        subscribeViewModel()
    }

    override fun onResume() {
        super.onResume()
        // tell the view model to update the values
        homeViewModel.active.onNext(true)
    }

    override fun onPause() {
        // tell the view model we're about to end -> no more updates needed
        homeViewModel.active.onNext(false)
        // clean up all pending subscriptions
        for (singleDisposable in disposables) {
            singleDisposable.dispose()
        }
        disposables.clear()
        super.onPause()
    }

    private fun subscribeViewModel() {
        // subscribe to new display information from the view model
        disposables.add(
                homeViewModel.blocks.subscribeBy(onNext = {
                    for (blockView in it) {
                        blockArea.addView(blockView as View)
                    }
                })
        )
    }
}