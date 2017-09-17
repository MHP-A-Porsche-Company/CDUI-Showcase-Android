package com.mhp.showcase.block.user

import android.annotation.SuppressLint
import android.content.Context
import android.widget.RelativeLayout
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.block.BaseBlockView
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@SuppressLint("ViewConstructor")
@EViewGroup(R.layout.view_block_user)
open class UserBlockView( context: Context) : RelativeLayout(context), BaseBlockView<UserBlock> {


    @ViewById(R.id.name)
    protected lateinit var nameTextView: TextView

    override var block: UserBlock? = null
        set(value) {
            afterViews()
        }


    @AfterViews
    override fun afterViews() {
        nameTextView.text = block?.user?.name
    }
}