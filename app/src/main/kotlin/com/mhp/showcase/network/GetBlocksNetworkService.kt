package com.mhp.showcase.network

import android.os.Handler
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.network.model.BlockResponse
import com.mhp.showcase.util.Constants
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import javax.inject.Inject

/**
 * Network service to get the definition of blocks for the home screen
 */
class GetBlocksNetworkService : NetworkService() {

    private val TAG = GetBlocksNetworkService::class.java.simpleName

    @Inject
    internal lateinit var requestQueue: RequestQueue
    @Inject
    internal lateinit var gson: Gson

    init {
        ShowcaseApplication.graph.inject(this)
    }

    val blocks: Observable<BlockResponse>
        get() = Observable.create { e ->
            startRequesting(e)
        }

    private fun startRequesting(e: ObservableEmitter<BlockResponse>) {
        val jsObjRequest = StringRequest(
                Request.Method.GET,
                Constants.URL,
                Response.Listener {
                    val blockResponse = gson.fromJson(it, BlockResponse::class.java)
                    e.onNext(blockResponse)
                    Handler().postDelayed({ startRequesting(e) }, 300)
                },
                Response.ErrorListener {
                    Log.d(TAG, "Network error occurred", it)
                    e.onError(it)
                }
        )
        requestQueue.add(jsObjRequest)
    }
}