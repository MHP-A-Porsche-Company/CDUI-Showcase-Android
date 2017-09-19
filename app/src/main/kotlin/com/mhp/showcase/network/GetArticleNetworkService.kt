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
 * Network service to get the definition of blocks for the home screen
 */
class GetArticleNetworkService {

    private val TAG = GetArticleNetworkService::class.java.simpleName

    @Inject
    internal lateinit var requestQueue: RequestQueue
    @Inject
    internal lateinit var gson: Gson


    fun getBlocks(id: String): Observable<ContentResponse> {
        return Observable.create<ContentResponse>({ it ->
            this.startRequesting(e = it, id = id)
        })
    }


    private fun startRequesting(id: String, e: ObservableEmitter<ContentResponse>) {
        if (e.isDisposed) {
            return
        }
        val jsObjRequest = JsonObjectRequest(
                Request.Method.GET,
                Constants.URL_ARTICLE + "-" + id + ".json", null,
                Response.Listener {
                    val blockResponse = gson.fromJson(it.toString(), ContentResponse::class.java)
                    e.onNext(blockResponse)
                    Handler().postDelayed({ startRequesting(id, e) }, 2000)
                },
                Response.ErrorListener {
                    Log.d(TAG, "Network error occurred", it)
                    e.onError(it)
                }
        )
        jsObjRequest.setShouldCache(false)
        requestQueue.add(jsObjRequest)
    }

    init {
        ShowcaseApplication.graph.inject(this)
    }
}