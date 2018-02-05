package com.mhp.showcase.fragment

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.BlockRecyclerViewAdapter
import com.mhp.showcase.model.view.HomeViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

@EFragment(R.layout.fragment_article)
open class StreamFragment : Fragment() {

    // The area to display the blocks in
    @ViewById(R.id.block_area)
    internal lateinit var blockArea: RecyclerView
    @ViewById(R.id.title)
    internal lateinit var titleTextView: TextView
    // The view model to get the values to display from
    @Inject
    internal lateinit var homeViewModel: HomeViewModel

    private val adapter = BlockRecyclerViewAdapter()

    // to keep track on the open subscriptions
    private val disposables: ArrayList<Disposable> = ArrayList()

    /**
     * Gets called after the view is inflated and the references are bound to the code
     */
    @AfterViews
    protected fun afterViews() {
        ShowcaseApplication.graph.inject(this)
    }

    override fun onResume() {
        super.onResume()
        // tell the view model to update the values
        homeViewModel.active.onNext(true)
        subscribeViewModel()

    }

    override fun onPause() {
        // tell the view model we're about to end -> no more updates needed
        homeViewModel.active.onNext(false)
        // clean up all pending subscriptions
        disposables.forEach(Disposable::dispose)
        disposables.clear()
        super.onPause()
    }

    /**
     * subscribe the view to the view model
     */
    private fun subscribeViewModel() {
        blockArea.adapter = adapter

        // subscribe to new display information from the view model
        disposables.add(
                homeViewModel.blocks.subscribeBy(onNext = {
                    adapter.updateList(it)
                })
        )
        disposables.add(homeViewModel.title.subscribeBy { title ->
            this.titleTextView.text = title
            if (title.isNotEmpty()) {
                titleTextView.visibility = View.VISIBLE
            }
        })
    }
}