package com.mhp.showcase.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.util.Constants
import io.reactivex.Observable
import javax.inject.Inject

class GetBlocksNetworkService : NetworkService() {

    private val TAG = GetBlocksNetworkService::class.java.simpleName

    @Inject
    protected lateinit var requestQueue: RequestQueue

    init {
        ShowcaseApplication.graph.inject(this)
    }

    val blocks: Observable<String>
        get() = Observable.create { e ->
            val jsObjRequest = StringRequest(
                    Request.Method.GET,
                    Constants.URL,
                    Response.Listener {
                        e.onNext(it)
                        e.onComplete()
                    },
                    Response.ErrorListener {}
            )
            requestQueue!!.add(jsObjRequest)
        }
}