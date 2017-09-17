package com.mhp.showcase.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewTreeObserver
import com.mhp.showcase.R


class FixedAspectBackendImageView(context: Context, attrs: AttributeSet?) : BackendImageView(context, attrs) {


    var aspect: Float = 0.toFloat()

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.FixedAspectBackendImageView, 0, 0)
        try {
            aspect = ta.getFloat(R.styleable.FixedAspectBackendImageView_aspect, 1f)
        } finally {
            ta.recycle()
        }

        // Set the height according to the current width and the aspect ratio
        viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                if (width > 0) {
                    viewTreeObserver.removeOnPreDrawListener(this)
                    layoutParams.height = (width / aspect).toInt()
                    invalidate()
                }
                return true
            }
        })
    }
}
