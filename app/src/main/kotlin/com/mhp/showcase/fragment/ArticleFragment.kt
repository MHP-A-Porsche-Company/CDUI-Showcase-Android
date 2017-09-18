package com.mhp.showcase.fragment

import android.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.BlockRecyclerViewAdapter
import com.mhp.showcase.model.view.ArticleViewModel
import com.mhp.showcase.view.FixedAspectBackendImageView
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.FragmentArg
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

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
    internal lateinit var titleTextView: TextView
    // The view model to get the values to display from
    @Inject
    internal lateinit var articleViewModel: ArticleViewModel
    // The id of the article to de bisplayed
    @FragmentArg("ID")
    internal lateinit var id: String

    // to keep track on the open subscriptions
    private val disposables: ArrayList<Disposable> = ArrayList()
    private val adapter = BlockRecyclerViewAdapter()

    override fun onResume() {
        super.onResume()
        // tell the view model to update the values
        articleViewModel.active.onNext(true)
    }

    override fun onPause() {
        // tell the view model we're about to end -> no more updates needed
        articleViewModel.active.onNext(false)
        // clean up all pending subscriptions
        disposables.forEach(Disposable::dispose)
        disposables.clear()
        super.onPause()
    }

    /**
     * Gets called after the view is inflated and the references are bound to the code
     */
    @AfterViews
    protected fun afterViews() {
        ShowcaseApplication.graph.inject(this)
        subscribeViewModel()

        // get the calculated height of the inflated title image
        heroImageView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(view: View?, var1: Int, top: Int, var2: Int, bottom: Int, var3: Int, var4: Int, var5: Int, var6: Int) {
                val height = bottom - top
                if (height > 0) {
                    blockArea.setPadding(0, height, 0, 0)
                    heroImageView.removeOnLayoutChangeListener(this)
                }
            }
        })

        // apply a parallax effect to the title image when the content scrolls
        blockArea.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                applyParallaxToTitleImage(blockArea.computeVerticalScrollOffset())
            }
        })
    }

    /**
     * Apply parallax effect to the title image
     */
    private fun applyParallaxToTitleImage(scrollY: Int) {
        Log.w("Scrolling", "Value: " + scrollY)
        val layoutParams = heroImageView.layoutParams as RelativeLayout.LayoutParams
        var parallaxValue = (-0.5 * scrollY).toInt()
        if (parallaxValue > 0) {
            parallaxValue = 0
        }
        layoutParams.setMargins(0, parallaxValue, 0, 0)
        heroImageView.layoutParams = layoutParams
        heroImageView.invalidate()
        heroImageView.requestLayout()
    }

    /**
     * subscribe the view to the view model
     */
    private fun subscribeViewModel() {
        blockArea.adapter = adapter
        // subscribe to new display information from the view model
        disposables.add(
                articleViewModel.blocks.subscribeBy(onNext = {
                    adapter.setBlocks(it)
                    adapter.notifyDataSetChanged()
                })
        )
        disposables.add(articleViewModel.title.subscribeBy { title -> this.titleTextView.text = title })
        disposables.add(articleViewModel.image.subscribeBy { uri -> this.heroImageView.url = uri })
    }
}