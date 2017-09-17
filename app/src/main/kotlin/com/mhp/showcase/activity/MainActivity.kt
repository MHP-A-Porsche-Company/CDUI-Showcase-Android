package com.mhp.showcase.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.FrameLayout
import com.mhp.showcase.R
import com.mhp.showcase.fragment.StreamFragment
import com.mhp.showcase.fragment.StreamFragment_
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById

@SuppressLint("Registered")// The generated MainActivity_ is registered in the manifest file
@EActivity(R.layout.activity_main)
open class MainActivity : Activity() {

    @ViewById(R.id.fragment_container)
    internal lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @AfterViews
    fun afterViews() {
        val streamFragment: StreamFragment = StreamFragment_.builder().build()
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, streamFragment)
        ft.commit()
    }
}