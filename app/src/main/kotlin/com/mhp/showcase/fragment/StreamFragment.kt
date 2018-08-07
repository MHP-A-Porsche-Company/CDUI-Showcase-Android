package com.mhp.showcase.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.BlockRecyclerViewAdapter
import com.mhp.showcase.model.view.HomeViewModel
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

@EFragment(R.layout.fragment_article)
open class StreamFragment : Fragment() {

    // The area to display the blocks in
    @ViewById(R.id.block_area)
    protected lateinit var blockArea: RecyclerView
    @ViewById(R.id.title)
    protected lateinit var titleTextView: TextView
    // The view model to get the values to display from
    private lateinit var homeViewModel: HomeViewModel
    private val adapter = BlockRecyclerViewAdapter()

    /**
     * Gets called after the view is inflated and the references are bound to the code
     */
    @AfterViews
    protected fun afterViews() {
        ShowcaseApplication.graph.inject(this)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        subscribeViewModel()
    }


    /**
     * subscribe the view to the view model
     */
    private fun subscribeViewModel() {
        blockArea.adapter = adapter

        // subscribe to new display information from the view model
        homeViewModel.blocks.observe(this, Observer { blocks ->
            blocks?.let { adapter.updateList(it) }
        })
        homeViewModel.title.observe(this, Observer { title ->
            this.titleTextView.text = title
            if (title != null && title.isNotEmpty()) {
                titleTextView.visibility = View.VISIBLE
            }
        })
    }
}