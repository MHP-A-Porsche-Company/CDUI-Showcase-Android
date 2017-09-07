package com.mhp.showcase.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.FrameLayout
import com.mhp.showcase.R
import com.mhp.showcase.fragment.HomeFragment
import com.mhp.showcase.fragment.HomeFragment_
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById

@SuppressLint("Registered")// The generated MainActivity_ is registered in the manifest file
@EActivity(R.layout.activity_main)
open class MainActivity : Activity() {

    @ViewById(R.id.fragment_container)
    protected lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @AfterViews
    fun afterViews() {
        val homeFragment: HomeFragment = HomeFragment_.builder().build()
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, homeFragment)
        ft.commit()
    }
}
