package com.mhp.showcase.block.user

import android.annotation.SuppressLint
import android.content.Context
import android.widget.RelativeLayout
import com.mhp.showcase.R
import com.mhp.showcase.block.BaseBlockView
import com.mhp.showcase.view.UserView
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@SuppressLint("ViewConstructor")
@EViewGroup(R.layout.view_block_user)
open class UserBlockView( context: Context) : RelativeLayout(context), BaseBlockView<UserBlock> {

    @ViewById(R.id.userView)
    protected lateinit var userView: UserView

    override var block: UserBlock? = null
        set(value) {
            field = value
            afterViews()
        }


    @AfterViews
    override fun afterViews() {
        userView.user = block?.user
    }
}