package com.mhp.showcase.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.BlockRecyclerViewAdapter
import com.mhp.showcase.model.view.ArticleViewModel
import com.mhp.showcase.view.FixedAspectBackendImageView
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.FragmentArg
import org.androidannotations.annotations.ViewById

/**
 * The fragment to display an article
 */
@EFragment(R.layout.fragment_article)
open class ArticleFragment : Fragment() {

    // The area to display the blocks in
    @ViewById(R.id.hero)
    internal lateinit var heroImageView: FixedAspectBackendImageView
    @ViewById(R.id.block_area)
    internal lateinit var blockArea: RecyclerView
    @ViewById(R.id.title)
    lateinit var titleTextView: TextView
    // The id of the article to de displayed
    @FragmentArg("ID")
    internal lateinit var id: String
    private val adapter = BlockRecyclerViewAdapter()
    private var heroHeight: Int = 0
    // The view model to get the values to display from
    private lateinit var articleViewModel: ArticleViewModel


    /**
     * Gets called after the view is inflated and the references are bound to the code
     */
    @AfterViews
    protected fun afterViews() {

        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        // tell the view model to update the values
        articleViewModel.id = id
        ShowcaseApplication.graph.inject(this)
        subscribeViewModel()
        // get the calculated height of the inflated title image
        heroImageView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(view: View?, var1: Int, top: Int, var2: Int, bottom: Int, var3: Int, var4: Int, var5: Int, var6: Int) {
                val height = bottom - top
                if (height > 0) {
                    blockArea.setPadding(0, height, 0, 0)
                    heroHeight = height
                    heroImageView.removeOnLayoutChangeListener(this)
                    heroImageView.visibility = View.GONE
                }
            }
        })

        // apply a parallax effect to the title image when the content scrolls
        blockArea.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val c: View = blockArea.getChildAt(0)
                applyParallaxToTitleImage((-c.top + blockArea.top + heroHeight))
            }
        })
    }

    /**
     * Apply parallax effect to the title image
     */
    private fun applyParallaxToTitleImage(scrollY: Int) {
        val layoutParams = heroImageView.layoutParams as RelativeLayout.LayoutParams
        val parallaxValue = (-0.5 * scrollY).toInt()
        layoutParams.setMargins(0, parallaxValue, 0, 0)
        heroImageView.layoutParams = layoutParams
    }

    /**
     * subscribe the view to the view model
     */
    private fun subscribeViewModel() {
        blockArea.adapter = adapter
        // subscribe to new display information from the view model
        articleViewModel.blocks.observe(this, Observer { blocks ->
            blocks?.let { it -> adapter.updateList(it) }
        })
        // subscribe to the title of the article
        articleViewModel.title.observe(this, Observer {
            this.titleTextView.text = it
            if (it != null && it.isNotEmpty()) {
                titleTextView.visibility = View.VISIBLE
            }

        })
        // subscribe to the title image of the article
        articleViewModel.image.observe(this, Observer {
            this.heroImageView.visibility = View.VISIBLE
            this.heroImageView.url = it
        })
    }
}