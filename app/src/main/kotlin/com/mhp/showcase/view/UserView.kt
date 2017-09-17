package com.mhp.showcase.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import com.mhp.showcase.R
import com.mhp.showcase.model.network.User
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@EViewGroup(R.layout.view_user)
open class UserView : RelativeLayout {

    @ViewById(R.id.name)
    lateinit var nameView: TextView
    @ViewById(R.id.position)
    lateinit var positionView: TextView
    @ViewById(R.id.avatar)
    lateinit var avatarView: BackendImageView

    var user: User? = null
        set(value) {
            field = value
            afterViews()
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)


    @AfterViews
    protected fun afterViews() {

        nameView.text = user?.name
        positionView.text = user?.position
        avatarView.url = user?.imageUrl
    }


}