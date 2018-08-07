package com.mhp.showcase.network

import android.os.Handler
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.network.model.ContentResponse
import com.mhp.showcase.util.Constants
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import javax.inject.Inject

/**
 * Network service to get the definition of blocks for the stream screen
 */
class GetStreamNetworkService {

    private val tag = GetStreamNetworkService::class.java.simpleName

    @Inject
    internal lateinit var requestQueue: RequestQueue
    @Inject
    internal lateinit var gson: Gson

    init {
        ShowcaseApplication.graph.inject(this)
    }

    val blocks: Observable<ContentResponse>
        get() = Observable.create { it -> this.startRequesting(e = it) }

    private fun startRequesting(e: ObservableEmitter<ContentResponse>) {
        if (e.isDisposed) {
            return
        }

        val jsObjRequest = JsonObjectRequest(
                Request.Method.GET,
                Constants.URL_STREAM, null,
                Response.Listener {
                    val blockResponse = gson.fromJson(it.toString(), ContentResponse::class.java)
                    e.onNext(blockResponse)
                    Handler().postDelayed({ startRequesting(e) }, 500)
                },
                Response.ErrorListener {
                    Log.d(tag, "Network error occurred", it)
                }
        )
        jsObjRequest.setShouldCache(false)
        requestQueue.add(jsObjRequest)
    }
}