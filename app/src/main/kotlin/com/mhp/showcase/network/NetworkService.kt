package com.mhp.showcase.network

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.VolleyError


/**
 * Base class for network communication. Builds and handles requests and delivers responses
 */
abstract class NetworkService {

    private val TAG = NetworkService::class.java.simpleName

    /**
     * 10 Minutes
     */
    //    protected final static long DEFAULT_CACHING_TIME = 5000;
    internal val TIME_OUT = 408


    /**
     * Get the network response from the [VolleyError]
     *
     * @param error The error that occurred
     * @return The HTTP error code
     */
    internal fun getErrorCode(error: VolleyError): Int {
        return if (error.networkResponse == null) {
            TIME_OUT
        } else {
            error.networkResponse.statusCode
        }
    }

    /**
     * Add content type and authentication headers to request
     *
     * @param request   The Request to add the headers to
     * @param authToken the authentication token to be used
     */
    internal fun addRequestHeaders(request: Request<*>, authToken: String) {
        try {
            request.headers.put("Authorization", "bearer " + authToken)
            request.headers.put("Accept", "*/*")
        } catch (authFailureError: AuthFailureError) {
            Log.w(TAG, "could not add header params", authFailureError)
        }

    }
}