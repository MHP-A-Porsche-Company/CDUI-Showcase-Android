package com.mhp.showcase.module

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.model.view.HomeViewModel
import com.mhp.showcase.network.GetBlocksNetworkService
import com.mhp.showcase.util.BlockViewHelper
import com.mhp.showcase.util.GsonBlockAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ShowcaseModule {

    private val TAG = ShowcaseModule::class.java.simpleName

    @Provides
    @Singleton
    protected fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        try {
            gsonBuilder.registerTypeAdapter(BaseBlock::class.java, GsonBlockAdapter())
        } catch (e: Throwable) {
            Log.w(TAG, "error registering type adapter for GSON deserialization", e)
        }
        return gsonBuilder.create()
    }

    @Provides
    protected fun provideContext(): Context {
        return ShowcaseApplication.getContext()
    }

    @Provides
    protected fun provideRequestQueue(context: Context): RequestQueue {
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
    protected fun provideGetBlocksNetworkService(): GetBlocksNetworkService {
        return GetBlocksNetworkService()
    }

    @Provides
    protected fun provideBlockViewHelper(): BlockViewHelper {
        return BlockViewHelper()
    }

    @Provides
    protected fun provideHomeViewModel(): HomeViewModel {
        return HomeViewModel()
    }
}