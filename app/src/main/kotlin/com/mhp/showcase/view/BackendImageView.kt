package com.mhp.showcase.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.ViewTreeObserver
import com.koushikdutta.ion.Ion
import com.mhp.showcase.R
import com.mhp.showcase.ShowcaseApplication
import java.net.URI

open class BackendImageView(theContext: Context, attrs: AttributeSet?) : AppCompatImageView(theContext, attrs) {


    var url: URI? = null
        set(url) {
            field = url
            viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    if (width > 0) {
                        visibility = getVisibility()
                        loadImageIntoView()
                        viewTreeObserver.removeOnPreDrawListener(this)
                    }
                    return true
                }
            })
        }
    internal var visibility: Int = 0


    init {
        ShowcaseApplication.graph.inject(this)
    }


    private fun loadImageIntoView() {
        // prepare the placeholder using a background color definition. (Android does not allow
        // programmatically defining dimensions for {@link ColorDrawable}
        // build the request


        //Build a transparent url to allow resizing of the placeholder
        val drawable = context.getDrawable(R.drawable.image_placeholder) as GradientDrawable
        setImageDrawable(drawable)
        // actual request for the url
        Ion.with(context).load(url.toString())
                // get the results as json
                .asBitmap().setCallback({ _, result ->
            setImageBitmap(result)
        })
        invalidate()
    }
}

