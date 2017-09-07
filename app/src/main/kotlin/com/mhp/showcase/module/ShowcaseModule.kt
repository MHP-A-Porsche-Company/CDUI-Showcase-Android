package com.mhp.showcase.module

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.network.GetBlocksNetworkService
import com.mhp.showcase.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ShowcaseModule {

    @Provides
    @Singleton
    protected fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Named(value = Constants.BAR_KEY)
    protected fun provideBar(): String {
        return "BAR"
    }

    @Provides
    internal fun provideContext(): Context {
        return ShowcaseApplication.getContext()
    }

    @Provides
    internal fun provideRequestQueue(context: Context): RequestQueue {
        val cache = DiskBasedCache(context.cacheDir, 1024 * 1024) // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        val network = BasicNetwork(HurlStack())
        // Instantiate the RequestQueue with the cache and network.
        val requestQueue = RequestQueue(cache, network)
        // Start the queue
        requestQueue.start()
        return requestQueue
    }

    @Provides
    internal fun provideGetBlocksNetworkService(): GetBlocksNetworkService {
        return GetBlocksNetworkService()
    }
}