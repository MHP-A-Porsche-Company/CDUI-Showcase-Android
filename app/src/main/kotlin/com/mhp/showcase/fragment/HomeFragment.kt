package com.mhp.showcase.fragment

import android.app.Fragment
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.network.GetBlocksNetworkService
import com.mhp.showcase.util.Constants
import io.reactivex.rxkotlin.subscribeBy
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import org.androidannotations.annotations.res.StringRes
import javax.inject.Inject
import javax.inject.Named

@EFragment(R.layout.fragment_home)
open class HomeFragment : Fragment() {

    @ViewById(R.id.text)
    protected lateinit var text: TextView

    @Inject
    protected lateinit var getBlocksNetworkService: GetBlocksNetworkService

    @field:[Inject Named(Constants.BAR_KEY)]
    lateinit var bar: String

    @StringRes(R.string.foo)
    protected lateinit var foo: String

    @AfterViews
    protected fun afterViews() {
        ShowcaseApplication.graph.inject(this)
        text.text = foo + bar
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        getBlocksNetworkService.blocks.subscribeBy { s -> text.text = s }
    }
}